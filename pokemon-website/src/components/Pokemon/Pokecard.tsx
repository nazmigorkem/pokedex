import React from 'react';
import PokemonTypeChips from './PokemonTypeChips';
import { motion } from 'framer-motion';

const Pokecard = React.forwardRef(
	(
		{
			imageURL,
			description,
			types,
		}: {
			imageURL: string;
			description: string;
			types: [string] | [string, string];
		},
		ref: any
	) => {
		return (
			<div ref={ref} className="h-[30rem] bg-base-300 rounded-md p-5 flex flex-col gap-5 pokecard">
				<div className="h-3/5">
					<img src={imageURL} className="self-center" />
				</div>
				<div className="grid grid-cols-2 gap-3">
					{types.map((type, index) => (
						<PokemonTypeChips key={index} typeName={type} />
					))}
				</div>
				<p className="pokecard_description text-sm h-2/5 overflow-hidden hover:overflow-y-auto p-2 pr-3 hover:pr-1 scroll-m-1 bg-base-200 text-base-content">
					{description}
				</p>
			</div>
		);
	}
);

export default motion(Pokecard, { forwardMotionProps: true });
