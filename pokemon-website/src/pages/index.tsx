import InfiniteScrollPokelist from '#/components/main/view/InfiniteScrollPokelist';
import { SERVER_URL } from '#/endpoints/Fetcher';
import { SERVER_POKEMON_ENDPOINT } from '#/endpoints/Pokemon';
import { useState, useEffect } from 'react';

export default function Home() {
	const [hasMore, setHasMore] = useState(true);
	const [pageNumber, setPageNumber] = useState(0);
	const [items, setItems] = useState([] as any[]);

	const fetchPokemons = () => {
		fetch(`${SERVER_URL}${SERVER_POKEMON_ENDPOINT}?pageNumber=${pageNumber}&pageSize=5`).then((response) => {
			console.log(response.status, pageNumber, hasMore);

			if (response.status !== 200) {
				setHasMore(false);
				return;
			}
			response.json().then((data) => {
				console.log(data);
				setPageNumber(pageNumber + 1);
				setHasMore(!data.last);
				setItems(items.concat(data.content));
			});
		});
	};

	return (
		<div className="w-5/6 float-right min-h-screen">
			<InfiniteScrollPokelist items={items} fetchFunction={fetchPokemons} hasMore={hasMore} />
		</div>
	);
}
