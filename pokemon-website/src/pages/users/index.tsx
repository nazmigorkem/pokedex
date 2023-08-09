import { UserSearchResponse, hasRoles } from '#/Types/User';
import { useContainerContext } from '#/components/main/view/Container';
import InfiniteScrollUserList from '#/components/main/view/InfiniteScrollUserList';
import { SERVER_URL, fetchForInfiniteScroll } from '#/endpoints/Fetcher';
import { USER_SERVER_ENDPOINTS } from '#/endpoints/User';
import { useRouter } from 'next/router';
import { useState } from 'react';

export default function Users() {
	const { heartbeatInfo } = useContainerContext();
	const router = useRouter();
	if (!hasRoles(heartbeatInfo.heartbeat, ['ROLE_ADMIN'])) {
		router.push('/');
		return <></>;
	}

	const [hasMore, setHasMore] = useState(true);
	const [pageNumber, setPageNumber] = useState(0);
	const [items, setItems] = useState([] as UserSearchResponse[]);
	const [searchValue, setSearchValue] = useState('');
	const fetchUsers = fetchForInfiniteScroll(
		`${SERVER_URL}${USER_SERVER_ENDPOINTS.SEARCH}`,
		pageNumber,
		items,
		searchValue,
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
					placeholder="Search for a user"
				/>
				<button
					onClick={() => {
						router.push(`/users/search/${searchValue}`);
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
