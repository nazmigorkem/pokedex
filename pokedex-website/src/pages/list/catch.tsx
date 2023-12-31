import Trainer from '#/components/main/session/auth/Trainer';
import InfiniteScrollPokelist from '#/components/main/view/InfiniteScrollPokelist';
import { fetchForInfiniteScroll } from '#/endpoints/Fetcher';
import { USER_SERVER_ENDPOINTS } from '#/endpoints/User';
import { useState } from 'react';

export default function Catch() {
	const [hasMore, setHasMore] = useState(true);
	const [pageNumber, setPageNumber] = useState(0);
	const [items, setItems] = useState([] as any[]);

	const fetchPokemons = fetchForInfiniteScroll(USER_SERVER_ENDPOINTS.CATCH_LIST.GET, pageNumber, items, '', setHasMore, setPageNumber, setItems);

	return <InfiniteScrollPokelist items={items} fetchFunction={fetchPokemons} hasMore={hasMore} />;
}

Catch.Auth = Trainer;
