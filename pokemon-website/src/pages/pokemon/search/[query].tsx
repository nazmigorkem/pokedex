import Trainer from '#/components/main/session/auth/Trainer';
import InfiniteScrollPokelist from '#/components/main/view/InfiniteScrollPokelist';
import { SERVER_URL, fetchForInfiniteScroll } from '#/endpoints/Fetcher';
import { POKEMON_SERVER_ENDPOINTS } from '#/endpoints/Pokemon';
import { GetServerSideProps } from 'next';
import { useRouter } from 'next/router';
import { useState } from 'react';

export default function Search({ query }: { query: string }) {
	const router = useRouter();
	const [hasMore, setHasMore] = useState(true);
	const [pageNumber, setPageNumber] = useState(0);
	const [items, setItems] = useState([] as any[]);
	const [searchValue, setSearchValue] = useState('');
	const fetchPokemons = fetchForInfiniteScroll(
		`${SERVER_URL}${POKEMON_SERVER_ENDPOINTS.SEARCH}`,
		pageNumber,
		items,
		query,
		setHasMore,
		setPageNumber,
		setItems
	);

	return (
		<div className="flex flex-col items-center mt-20">
			<div className="flex gap-3">
				<input
					onChange={(e) => {
						setSearchValue(e.target.value);
					}}
					type="text"
					className="input input-accent input-bordered"
					placeholder="Search for a pokemon"
				/>
				<button
					onClick={() => {
						router.replace(`/pokemon/search/${searchValue}`, undefined, { unstable_skipClientCache: true }).then(() => router.reload());
					}}
					className="btn btn-outline btn-accent"
				>
					<i className="fas fa-search"></i>
				</button>
			</div>
			<InfiniteScrollPokelist items={items} fetchFunction={fetchPokemons} hasMore={hasMore} />
		</div>
	);
}

Search.Auth = Trainer;

export const getServerSideProps: GetServerSideProps<{
	query: string;
}> = async ({ query }) => {
	return { props: { query: query.query as string } };
};
