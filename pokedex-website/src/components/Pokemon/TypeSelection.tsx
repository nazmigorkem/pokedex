import { Dispatch, SetStateAction } from 'react';

export default function TypeSelection({
	pokemonTypes,
	properties,
	setProperties,
}: {
	pokemonTypes?: PokemonTypeResponse[];
	properties: PokemonEditRequest | PokemonAddRequest;
	setProperties: Dispatch<SetStateAction<PokemonEditRequest>> | Dispatch<SetStateAction<PokemonAddRequest>>;
}) {
	return (
		<div>
			<label className="label">Types</label>
			<div className="grid grid-cols-3 gap-5">
				{pokemonTypes?.map((type) => (
					<button
						disabled={properties.types.length === 2 && !properties.types.includes(type.name)}
						onClick={() => {
							const propertyClone = { ...properties };
							if (propertyClone?.types.some((x) => x === type.name) && propertyClone?.types.length >= 2) {
								propertyClone.types = propertyClone.types.filter((x) => x !== type.name) as [string, string];
							} else if (
								(propertyClone?.types.some((x) => x !== type.name) && propertyClone.types.length < 2) ||
								// @ts-ignore
								propertyClone.types.length === 0
							) {
								if (propertyClone.types[0] === '') {
									propertyClone.types = [type.name];
								} else {
									propertyClone.types = [...propertyClone.types, type.name] as [string, string];
								}
							}
							// @ts-ignore
							setProperties(propertyClone);
						}}
						key={type.name}
						className={`btn btn-accent px-3 py-1 rounded-md text-center truncate ${
							properties?.types.some((x) => x === type.name) ? 'outline outline-2 outline-success outline-offset-2' : ''
						} ${
							properties.types.length === 1 && properties.types.includes(type.name) ? 'no-animation hover:bg-accent cursor-default' : ''
						}`}
					>
						<span className="">{type.name}</span>
					</button>
				))}
			</div>
		</div>
	);
}
