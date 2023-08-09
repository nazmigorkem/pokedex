import { hasRoles } from '#/Types/User';
import { useContainerContext } from '#/components/main/view/Container';
import InfiniteScrollUserList from '#/components/main/view/InfiniteScrollUserList';
import { SERVER_URL, fetchForInfiniteScroll } from '#/endpoints/Fetcher';
import { USER_SERVER_ENDPOINTS } from '#/endpoints/User';
import { GetServerSideProps } from 'next';
import { useRouter } from 'next/router';
import { useState } from 'react';

export default function Search({ query }: { query: string }) {
	const { heartbeatInfo } = useContainerContext();
	const router = useRouter();
	const [hasMore, setHasMore] = useState(true);
	const [pageNumber, setPageNumber] = useState(0);
	const [items, setItems] = useState([] as any[]);
	const [searchValue, setSearchValue] = useState('');
	const fetchUsers = fetchForInfiniteScroll(
		`${SERVER_URL}${USER_SERVER_ENDPOINTS.SEARCH}`,
		pageNumber,
		items,
		query,
		setHasMore,
		setPageNumber,
		setItems
	);

	if (!hasRoles(heartbeatInfo.heartbeat, ['ROLE_TRAINER', 'ROLE_ADMIN'])) {
		router.push('/');
		return <></>;
	}

	return (
		<div className="flex flex-col items-center mt-20">
			<div className="flex gap-3">
				<input
					onChange={(e) => {
						setSearchValue(e.target.value);
					}}
					type="text"
					className="input input-accent input-bordered"
					placeholder="Search for a user"
				/>
				<button
					onClick={() => {
						router.replace(`/users/search/${searchValue}`, undefined, { unstable_skipClientCache: true }).then(() => router.reload());
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

export const getServerSideProps: GetServerSideProps<{
	query: string;
}> = async ({ query }) => {
	return { props: { query: query.query as string } };
};
