import InfiniteScrollPokelist from '#/components/main/view/InfiniteScrollPokelist';
import { SERVER_URL, fetchForInfiniteScroll } from '#/endpoints/Fetcher';
import { SERVER_USER_WISH_LIST_ENDPOINT } from '#/endpoints/User';
import { useState } from 'react';

export default function Wish() {
	const [hasMore, setHasMore] = useState(true);
	const [pageNumber, setPageNumber] = useState(0);
	const [items, setItems] = useState([] as any[]);

	const fetchPokemons = fetchForInfiniteScroll(
		`${SERVER_URL}${SERVER_USER_WISH_LIST_ENDPOINT}`,
		pageNumber,
		items,
		setHasMore,
		setPageNumber,
		setItems
	);

	return <InfiniteScrollPokelist items={items} fetchFunction={fetchPokemons} hasMore={hasMore} />;
}
