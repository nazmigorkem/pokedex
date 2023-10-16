import { hasRoles } from '#/Types/User';
import Pokecard from '#/components/Pokemon/Pokecard';
import SearchBars from '#/components/Pokemon/SearchBars';
import { useContainerContext } from '#/components/main/view/Container';
import InfiniteScrollPokelist from '#/components/main/view/InfiniteScrollPokelist';
import { fetchForInfiniteScroll } from '#/endpoints/Fetcher';
import { POKEMON_SERVER_ENDPOINTS } from '#/endpoints/Pokemon';
import { useState } from 'react';

export default function Home() {
	const { heartbeatInfo } = useContainerContext();
	const { heartbeat } = heartbeatInfo;
	const [hasMore, setHasMore] = useState(true);
	const [pageNumber, setPageNumber] = useState(0);
	const [items, setItems] = useState([] as PokemonResponse[]);
	const [searchValue, setSearchValue] = useState('');
	const fetchPokemons = fetchForInfiniteScroll(
		POKEMON_SERVER_ENDPOINTS.SEARCH,
		pageNumber,
		items,
		searchValue,
		setHasMore,
		setPageNumber,
		setItems
	);

	console.log(heartbeat);

	if (hasRoles(heartbeat, ['ROLE_ANONYMOUS'])) {
		return (
			<>
				<div className="fixed w-full min-h-screen backdrop-blur-[10px] z-10 transform-gpu"></div>
				<div className="w-full min-h-screen max-h-screen overflow-hidden grid grid-cols-5 gap-5 p-10">
					{Array.from(Array(10).keys()).map((index) => (
						<div className="relative primary">
							<Pokecard
								variants={{
									hidden: () => ({
										opacity: 0,
										x: -30,
									}),
									visible: (key) => ({
										opacity: 1,
										x: -0,
										transition: {
											delay: key * 0.1,
											bounce: false,
										},
									}),
								}}
								whileHover={{
									scale: 1.05,
									transition: {
										duration: 0.1,
									},
								}}
								whileTap={{
									scale: 0.95,
									transition: {
										duration: 0.1,
									},
								}}
								animate="visible"
								initial="hidden"
								pokemon={{
									name: 'Pikachu',
									types: [{ name: 'Electric', color: '#ffffff' }],
									imageUrl: `https://assets.pokemon.com/assets/cms2/img/pokedex/full/${(index + 1)
										.toString()
										.padStart(3, '0')}.png`,
									description: 'Pikachu is an Electric-type Pokémon introduced in Generation I.',
									attack: 55,
									defense: 40,
									health: 35,
									specialAttack: 50,
									specialDefense: 50,
									speed: 90,
								}}
							/>
						</div>
					))}

					{Array.from(Array(10).keys()).map((index) => (
						<div className="relative secondary">
							<Pokecard
								variants={{
									hidden: () => ({
										opacity: 0,
										x: -30,
									}),
									visible: (key) => ({
										opacity: 1,
										x: -0,
										transition: {
											delay: key * 0.1,
											bounce: false,
										},
									}),
								}}
								whileHover={{
									scale: 1.05,
									transition: {
										duration: 0.1,
									},
								}}
								whileTap={{
									scale: 0.95,
									transition: {
										duration: 0.1,
									},
								}}
								animate="visible"
								initial="hidden"
								pokemon={{
									name: 'Pikachu',
									types: [{ name: 'Electric', color: '#ffffff' }],
									imageUrl: `https://assets.pokemon.com/assets/cms2/img/pokedex/full/${(index + 1)
										.toString()
										.padStart(3, '0')}.png`,
									description: 'Pikachu is an Electric-type Pokémon introduced in Generation I.',
									attack: 55,
									defense: 40,
									health: 35,
									specialAttack: 50,
									specialDefense: 50,
									speed: 90,
								}}
							/>
						</div>
					))}
				</div>
			</>
		);
	} else {
		return (
			<div className="flex flex-col mt-20">
				<SearchBars searchValue={searchValue} setSearchValue={setSearchValue} />
				<InfiniteScrollPokelist items={items} fetchFunction={fetchPokemons} hasMore={hasMore} />
			</div>
		);
	}
}
