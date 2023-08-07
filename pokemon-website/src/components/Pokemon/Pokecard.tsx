import React from 'react';
import PokemonTypeChips from './PokemonTypeChips';
import { motion } from 'framer-motion';
import { useRouter } from 'next/router';

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
			<div ref={ref} className="h-[35rem] bg-base-300 rounded-md p-5 flex flex-col gap-2 pokecard">
				<div className="h-[475px]">
					<img src={pokemon.imageUrl} className="self-center" />
				</div>
				<div className="truncate h-1/6 rounded-md font-semibold flex items-center justify-center">{pokemon.name}</div>
				<div className="grid grid-cols-2 gap-3">
					{pokemon.types.map((type, index) => (
						<PokemonTypeChips key={index} typeName={type.name} typeColor={type.color} />
					))}
				</div>
				<p className="pokecard_description text-sm h-2/5 overflow-hidden hover:overflow-y-auto p-2 pr-3 hover:pr-1 scroll-m-1 bg-base-200 text-base-content">
					{pokemon.description}
				</p>
				<div className="w-full flex gap-5">
					<button
						className="btn btn-accent text-accent-content rounded-md flex-1"
						onClick={() => {
							router.push(`/pokemon/${pokemon.name.toLowerCase()}`);
						}}
					>
						View
					</button>
				</div>
			</div>
		);
	}
);

export default motion(Pokecard, { forwardMotionProps: true });
