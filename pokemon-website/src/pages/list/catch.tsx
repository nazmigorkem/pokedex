import { hasRoles } from '#/Types/User';
import { useContainerContext } from '#/components/main/view/Container';
import InfiniteScrollPokelist from '#/components/main/view/InfiniteScrollPokelist';
import { SERVER_URL, fetchForInfiniteScroll } from '#/endpoints/Fetcher';
import { USER_SERVER_ENDPOINTS } from '#/endpoints/User';
import { useRouter } from 'next/router';
import { useState } from 'react';

export default function Catch() {
	const { heartbeatInfo } = useContainerContext();
	const router = useRouter();
	const [hasMore, setHasMore] = useState(true);
	const [pageNumber, setPageNumber] = useState(0);
	const [items, setItems] = useState([] as any[]);

	const fetchPokemons = fetchForInfiniteScroll(
		`${SERVER_URL}${USER_SERVER_ENDPOINTS.CATCH_LIST.GET}`,
		pageNumber,
		items,
		'',
		setHasMore,
		setPageNumber,
		setItems
	);

	if (!hasRoles(heartbeatInfo.heartbeat, ['ROLE_TRAINER', 'ROLE_ADMIN'])) {
		router.push('/');
		return <></>;
	}

	return <InfiniteScrollPokelist items={items} fetchFunction={fetchPokemons} hasMore={hasMore} />;
}
