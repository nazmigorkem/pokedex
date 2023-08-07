import ListStateButton from '#/components/Pokemon/ListButton';
import PokemonTypeChips from '#/components/Pokemon/PokemonTypeChips';
import StatChip from '#/components/Pokemon/StatChip';
import { SERVER_URL, fetcher } from '#/endpoints/Fetcher';
import { POKEMON_SERVER_ENDPOINTS } from '#/endpoints/Pokemon';
import { USER_SERVER_ENDPOINTS } from '#/endpoints/User';
import { useRouter } from 'next/router';
import useSWR, { mutate } from 'swr';

export default function PokemonPage() {
	const router = useRouter();
	const { name } = router.query;
	const { data: pokemonData } = useSWR<PokemonResponse>(`${POKEMON_SERVER_ENDPOINTS.GET}/${name}`, fetcher);
	const { data: isExistsInCatchList } = useSWR<boolean>(
		pokemonData
			? `${USER_SERVER_ENDPOINTS.CATCH_LIST.IS_EXIST}?${new URLSearchParams({
					pokemonName: pokemonData.name,
			  })}`
			: null,
		fetcher
	);
	const { data: isExistsInWishList } = useSWR<boolean>(
		pokemonData
			? `${USER_SERVER_ENDPOINTS.WISH_LIST.IS_EXIST}?${new URLSearchParams({
					pokemonName: pokemonData.name,
			  })}`
			: null,
		fetcher
	);
	if (!pokemonData || isExistsInCatchList === undefined || isExistsInWishList === undefined) return <div className="loading loading-lg"></div>;

	return (
		<div className="m-20 min-h-[70vh] flex flex-col gap-10">
			<div className="flex items-center gap-3">
				<h1 className="text-5xl font-bold">{pokemonData.name}</h1>
				<ListStateButton
					pokemonData={pokemonData}
					isExists={isExistsInCatchList}
					iconString="fa-star"
					listEndpoint={USER_SERVER_ENDPOINTS.CATCH_LIST}
				/>
				<ListStateButton
					pokemonData={pokemonData}
					isExists={isExistsInWishList}
					iconString="fa-bookmark"
					listEndpoint={USER_SERVER_ENDPOINTS.WISH_LIST}
				/>
			</div>
			<div className="flex">
				<img src={pokemonData.imageUrl} className="aspect-square rounded-l-md bg-neutral bg-opacity-25 w-[475px]" alt="Pokemon image" />
				<div className="bg-neutral p-5 rounded-r-md border-base-300 flex-1">{pokemonData.description}</div>
			</div>
			<div className="flex justify-between items-start">
				<div className="flex flex-col gap-3">
					<h3 className="font-bold uppercase text-base">Types</h3>
					<div className="flex gap-5">
						{pokemonData.types.map((type, index) => (
							<PokemonTypeChips key={index} typeName={type.name} typeColor={type.color} />
						))}
					</div>
				</div>
				<div className="flex gap-5">
					<StatChip stat={pokemonData.attack} statName="Attack" />
					<StatChip stat={pokemonData.defense} statName="Defense" />
					<StatChip stat={pokemonData.health} statName="Health" />
					<StatChip stat={pokemonData.specialAttack} statName="S. Attack" />
					<StatChip stat={pokemonData.specialDefense} statName="S. Defense" />
					<StatChip stat={pokemonData.speed} statName="Speed" />
				</div>
			</div>
		</div>
	);
}
