import Trainer from '#/components/main/session/auth/Trainer';
import InfiniteScrollPokelist from '#/components/main/view/InfiniteScrollPokelist';
import { fetchForInfiniteScroll } from '#/endpoints/Fetcher';
import { USER_SERVER_ENDPOINTS } from '#/endpoints/User';
import { useState } from 'react';

export default function Wish() {
	const [hasMore, setHasMore] = useState(true);
	const [pageNumber, setPageNumber] = useState(0);
	const [items, setItems] = useState([] as any[]);

	const fetchPokemons = fetchForInfiniteScroll(USER_SERVER_ENDPOINTS.WISH_LIST.GET, pageNumber, items, '', setHasMore, setPageNumber, setItems);

	return <InfiniteScrollPokelist items={items} fetchFunction={fetchPokemons} hasMore={hasMore} />;
}

Wish.Auth = Trainer;
