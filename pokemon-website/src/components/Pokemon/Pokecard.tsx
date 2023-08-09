import { motion } from 'framer-motion';
import { useRouter } from 'next/router';
import React from 'react';
import PokemonTypeChips from './PokemonTypeChips';

const Pokecard = React.forwardRef(
	(
		{
			pokemon,
		}: {
			pokemon: PokemonResponse;
		},
		ref: any
	) => {
		const router = useRouter();
		return (
			<div
				onClick={() => {
					router.push(`/pokemon/${pokemon.name.toLowerCase()}`);
				}}
				ref={ref}
				className="h-[30rem] bg-base-300 rounded-md p-5 flex flex-col gap-2 pokecard cursor-pointer hover:shadow-lg"
			>
				<div className="truncate h-1/5 rounded-md font-bold text-xl text-center">{pokemon.name}</div>

				<div className="h-[475px] aspect-square bg-neutral bg-opacity-25">
					<img src={pokemon.imageUrl} />
				</div>
				<div className="grid grid-cols-2 gap-3">
					{pokemon.types.map((type, index) => (
						<PokemonTypeChips key={index} typeName={type.name} typeColor={type.color} />
					))}
				</div>
				<p className="pokecard_description text-sm h-2/5 overflow-hidden hover:overflow-y-auto pr-3 hover:pr-1 text-base-content font-semibold">
					{pokemon.description}
				</p>
			</div>
		);
	}
);

export default motion(Pokecard, { forwardMotionProps: true });
