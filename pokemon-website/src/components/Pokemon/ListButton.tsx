import { SERVER_URL } from '#/endpoints/Fetcher';
import { mutate } from 'swr';
import React from 'react';

export default function ListStateButton({
	pokemonData,
	isExists,
	iconString,
	listEndpoint,
}: {
	pokemonData: PokemonResponse;
	isExists: boolean;
	iconString: string;
	listEndpoint: { DELETE: string; ADD: string; IS_EXIST: string };
}) {
	return (
		<>
			{isExists && (
				<i
					className={`fas ${iconString} text-white text-3xl cursor-pointer`}
					onClick={async () => {
						const response = await fetch(`${SERVER_URL}${listEndpoint.DELETE}`, {
							method: 'POST',
							headers: {
								'Content-Type': 'application/json',
							},
							body: JSON.stringify({
								pokemonName: pokemonData.name,
							}),
						});

						if (response.status !== 200) return;

						mutate(`${listEndpoint.IS_EXIST}?${new URLSearchParams({ pokemonName: pokemonData.name })}`, undefined);
					}}
				/>
			)}
			{!isExists && (
				<i
					className={`far ${iconString} text-white text-3xl cursor-pointer`}
					onClick={async () => {
						const response = await fetch(
							`${SERVER_URL}${listEndpoint.ADD}?${new URLSearchParams({
								pokemonName: pokemonData.name,
							})}`,
							{
								method: 'POST',
								headers: { 'Content-Type': 'application/json' },
								body: JSON.stringify({
									pokemonName: pokemonData.name,
								}),
							}
						);

						if (response.status !== 200) return;

						mutate(`${listEndpoint.IS_EXIST}?${new URLSearchParams({ pokemonName: pokemonData.name })}`, undefined);
					}}
				/>
			)}
		</>
	);
}
