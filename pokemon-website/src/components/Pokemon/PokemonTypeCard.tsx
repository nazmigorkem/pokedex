import { SERVER_URL } from '#/endpoints/Fetcher';
import { POKEMON_SERVER_ENDPOINTS } from '#/endpoints/Pokemon';
import { motion } from 'framer-motion';
import { useRouter } from 'next/router';
import React from 'react';

const PokemonTypeCard = React.forwardRef(({ data }: { data: PokemonTypeResponse }, ref: any) => {
	const router = useRouter();
	return (
		<div ref={ref} className="flex flex-row items-center gap-5 bg-base-300 p-5 rounded-md">
			<button className="w-full text-left">{data.name}</button>
			<button
				onClick={(e) => {
					router.push(`/pokemon/types/${data.name}/edit`);
				}}
			>
				<i className="fas fa-pen btn btn-neutral btn-outline"></i>
			</button>
			<button
				onClick={async () => {
					const response = await fetch(`${SERVER_URL}${POKEMON_SERVER_ENDPOINTS.TYPES.DELETE}/${data.name}`, {
						method: 'DELETE',
					});

					console.log(response.status);
					if (response.status === 200) {
						router.replace('/');
					}
				}}
				className="btn btn-outline btn-error"
			>
				<i className="fas fa-trash" />
			</button>
		</div>
	);
});

export default motion(PokemonTypeCard, { forwardMotionProps: true });
