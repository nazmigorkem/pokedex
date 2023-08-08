import { ErrorResponse } from '#/Types/ErrorResponse';
import { hasRoles } from '#/Types/UserResponse';
import ListStateButton from '#/components/Pokemon/ListButton';
import PokemonTypeChips from '#/components/Pokemon/PokemonTypeChips';
import StatChip from '#/components/Pokemon/StatChip';
import { useContainerContext } from '#/components/main/view/Container';
import ErrorList from '#/components/main/view/ErrorList';
import { SERVER_URL, fetcher } from '#/endpoints/Fetcher';
import { POKEMON_SERVER_ENDPOINTS } from '#/endpoints/Pokemon';
import { USER_SERVER_ENDPOINTS } from '#/endpoints/User';
import { GetServerSideProps } from 'next';
import { useRouter } from 'next/router';
import { useState } from 'react';
import useSWR from 'swr';

export default function PokemonPage({ name }: { name: string }) {
	const { heartbeatInfo } = useContainerContext();
	const router = useRouter();
	if (!hasRoles(heartbeatInfo.heartbeat, ['ROLE_TRAINER', 'ROLE_ADMIN'])) {
		router.push('/');
		return <></>;
	}

	const [errors, setErrors] = useState<string[]>([]);
	const { data: pokemonData } = useSWR<PokemonResponse | ErrorResponse>(`${POKEMON_SERVER_ENDPOINTS.SEARCH}/${name}`, fetcher);
	const { data: isExistsInCatchList } = useSWR<boolean>(
		pokemonData && 'name' in pokemonData
			? `${USER_SERVER_ENDPOINTS.CATCH_LIST.IS_EXIST}?${new URLSearchParams({
					pokemonName: pokemonData.name,
			  })}`
			: null,
		fetcher
	);
	const { data: isExistsInWishList } = useSWR<boolean>(
		pokemonData && 'name' in pokemonData
			? `${USER_SERVER_ENDPOINTS.WISH_LIST.IS_EXIST}?${new URLSearchParams({
					pokemonName: pokemonData.name,
			  })}`
			: null,
		fetcher
	);

	if (pokemonData && 'errors' in pokemonData) return <div className="text-center text-2xl font-bold mt-20">{pokemonData.errors[0]}</div>;
	if (!pokemonData || isExistsInCatchList === undefined || isExistsInWishList === undefined)
		return (
			<div className="w-full min-h-screen flex items-center justify-center">
				<div className="loading loading-lg"></div>
			</div>
		);

	return (
		<div className="m-20 min-h-[70vh] flex flex-col gap-10">
			<ErrorList errors={errors} />
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
				{hasRoles(heartbeatInfo.heartbeat, ['ROLE_ADMIN']) && (
					<button
						onClick={async () => {
							const response = await fetch(`${SERVER_URL}${POKEMON_SERVER_ENDPOINTS.DELETE}/${pokemonData.name}`, {
								method: 'DELETE',
							});

							if (response.status === 200) {
								router.replace('/');
							} else {
								setErrors((await response.json()).errors);
							}
						}}
						className="btn btn-error btn-square btn-ghost btn-outline ml-auto"
					>
						<i className="fas fa-trash" />
					</button>
				)}
			</div>
			<div className="flex">
				<img src={pokemonData.imageUrl} className="aspect-square rounded-md bg-neutral w-[475px]" alt="Pokemon image" />
				<div className="flex-1 flex flex-col justify-between pl-5">
					<div className="bg-base-300 p-3 text-base-content h-1/2">{pokemonData.description}</div>
					<div className="flex justify-between items-start">
						<div className="flex flex-col gap-3 bg-base-300 rounded-md justify-center p-3">
							<h3 className="font-bold uppercase text-base-content">Types</h3>
							<div className="flex gap-5">
								{pokemonData.types.map((type, index) => (
									<PokemonTypeChips key={index} typeName={type.name} typeColor={type.color} />
								))}
							</div>
						</div>
						<div className="flex gap-5 h-full">
							<StatChip stat={pokemonData.attack} statName="Attack" />
							<StatChip stat={pokemonData.defense} statName="Defense" />
							<StatChip stat={pokemonData.health} statName="Health" />
							<StatChip stat={pokemonData.specialAttack} statName="S. Attack" />
							<StatChip stat={pokemonData.specialDefense} statName="S. Defense" />
							<StatChip stat={pokemonData.speed} statName="Speed" />
						</div>
					</div>
				</div>
			</div>
		</div>
	);
}

export const getServerSideProps: GetServerSideProps<{
	name: string;
}> = async ({ query }) => {
	return { props: { name: query.name as string } };
};
