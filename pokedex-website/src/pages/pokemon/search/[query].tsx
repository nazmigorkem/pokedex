import SearchBars from '#/components/Pokemon/SearchBars';
import Trainer from '#/components/main/session/auth/Trainer';
import InfiniteScrollPokelist from '#/components/main/view/InfiniteScrollPokelist';
import { fetchForInfiniteScroll } from '#/endpoints/Fetcher';
import { POKEMON_SERVER_ENDPOINTS } from '#/endpoints/Pokemon';
import { GetServerSideProps } from 'next';
import { useState } from 'react';

export default function Search({ query }: { query: string }) {
	const [hasMore, setHasMore] = useState(true);
	const [pageNumber, setPageNumber] = useState(0);
	const [items, setItems] = useState([] as any[]);
	const [searchValue, setSearchValue] = useState('');
	const fetchPokemons = fetchForInfiniteScroll(POKEMON_SERVER_ENDPOINTS.SEARCH, pageNumber, items, query, setHasMore, setPageNumber, setItems);

	return (
		<div className="flex flex-col mt-20">
			<SearchBars searchValue={searchValue} setSearchValue={setSearchValue} />
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
