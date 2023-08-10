import { hasRoles } from '#/Types/User';
import Pokecard from '#/components/Pokemon/Pokecard';
import { useContainerContext } from '#/components/main/view/Container';
import InfiniteScrollPokelist from '#/components/main/view/InfiniteScrollPokelist';
import { SERVER_URL, fetchForInfiniteScroll } from '#/endpoints/Fetcher';
import { POKEMON_SERVER_ENDPOINTS } from '#/endpoints/Pokemon';
import { useRouter } from 'next/router';
import { useState } from 'react';

export default function Home() {
	const { heartbeatInfo } = useContainerContext();
	const { heartbeat } = heartbeatInfo;
	const [hasMore, setHasMore] = useState(true);
	const [pageNumber, setPageNumber] = useState(0);
	const [items, setItems] = useState([] as PokemonResponse[]);
	const [searchValue, setSearchValue] = useState('');
	const router = useRouter();
	const fetchPokemons = fetchForInfiniteScroll(
		`${SERVER_URL}${POKEMON_SERVER_ENDPOINTS.SEARCH}`,
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
				<div className="flex gap-3 w-[25vw] mx-auto">
					<input
						onChange={(e) => {
							setSearchValue(e.target.value);
						}}
						type="text"
						className="input input-accent input-bordered w-full"
						placeholder="Search for a pokemon"
					/>
					<button
						onClick={() => {
							router.push(`/pokemon/search/${searchValue}`);
						}}
						className="btn btn-outline btn-accent"
					>
						<i className="fas fa-search"></i>
					</button>
				</div>
				<InfiniteScrollPokelist items={items} fetchFunction={fetchPokemons} hasMore={hasMore} />
			</div>
		);
	}
}
