import InfiniteScrollPokelist from '#/components/main/view/InfiniteScrollPokelist';
import { SERVER_URL, fetchForInfiniteScroll } from '#/endpoints/Fetcher';
import { POKEMON_SERVER_ENDPOINTS } from '#/endpoints/Pokemon';
import { useState } from 'react';

export default function Home() {
	const [hasMore, setHasMore] = useState(true);
	const [pageNumber, setPageNumber] = useState(0);
	const [items, setItems] = useState([] as any[]);

	const fetchPokemons = fetchForInfiniteScroll(
		`${SERVER_URL}${POKEMON_SERVER_ENDPOINTS.SEARCH}`,
		pageNumber,
		items,
		setHasMore,
		setPageNumber,
		setItems
	);

	return <InfiniteScrollPokelist items={items} fetchFunction={fetchPokemons} hasMore={hasMore} />;
}
