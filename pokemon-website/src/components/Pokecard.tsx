import React from 'react';
import PokemonTypeChips from './PokemonTypeChips';

export default function Pokecard({ imageURL, description, types }: { imageURL: string; description: string; types: [string] | [string, string] }) {
	return (
		<div className="h-[30rem] bg-base-300 rounded-md p-5 flex flex-col gap-5">
			<div className="h-3/5">
				<img src={imageURL} className="self-center" />
			</div>
			<div className="grid grid-cols-2 gap-3">
				{types.map((type) => (
					<PokemonTypeChips typeName={type} />
				))}
			</div>
			<p className="pokecard_description text-sm h-2/5 overflow-hidden hover:overflow-y-auto p-2 pr-3 hover:pr-1 scroll-m-1 bg-base-200 text-base-content">
				{description}
			</p>
		</div>
	);
}
