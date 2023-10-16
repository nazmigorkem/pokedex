import { UserSearchResponse } from '#/Types/User';
import Admin from '#/components/main/session/auth/Admin';
import InfiniteScrollUserList from '#/components/main/view/InfiniteScrollUserList';
import { fetchForInfiniteScroll } from '#/endpoints/Fetcher';
import { USER_SERVER_ENDPOINTS } from '#/endpoints/User';
import { GetServerSideProps } from 'next';
import { useRouter } from 'next/router';
import { useState } from 'react';

export default function Users({ query }: { query: string }) {
	const router = useRouter();

	const [hasMore, setHasMore] = useState(true);
	const [pageNumber, setPageNumber] = useState(0);
	const [items, setItems] = useState([] as UserSearchResponse[]);
	const [searchValue, setSearchValue] = useState('');
	const fetchUsers = fetchForInfiniteScroll(USER_SERVER_ENDPOINTS.SEARCH, pageNumber, items, query, setHasMore, setPageNumber, setItems);

	return (
		<div className="flex flex-col items-center mt-20 w-[25vw] mx-auto">
			<div className="flex gap-3 w-full">
				<input
					onChange={(e) => {
						setSearchValue(e.target.value);
					}}
					type="text"
					className="input input-accent input-bordered w-full"
					placeholder="Search for a user"
				/>
				<button
					onClick={() => {
						router.push(`/users/search/${searchValue}`).then(router.reload);
					}}
					className="btn btn-outline btn-accent"
				>
					<i className="fas fa-search"></i>
				</button>
			</div>
			<InfiniteScrollUserList items={items} fetchFunction={fetchUsers} hasMore={hasMore} />
		</div>
	);
}

Users.Auth = Admin;

export const getServerSideProps: GetServerSideProps<{
	query: string;
}> = async ({ query }) => {
	return { props: { query: query.query as string } };
};
