import { ErrorResponse } from '#/Types/ErrorResponse';
import Admin from '#/components/main/session/auth/Admin';
import CustomInput from '#/components/main/view/CustomInput';
import ErrorList from '#/components/main/view/ErrorList';
import FullScreenLoadingSpinner from '#/components/main/view/FullScreenLoadingSpinner';
import { SERVER_URL } from '#/endpoints/Fetcher';
import { POKEMON_SERVER_ENDPOINTS, usePokemon, usePokemonTypes } from '#/endpoints/Pokemon';
import { GetServerSideProps } from 'next';
import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';

export default function Edit({ name }: { name: string }) {
	const router = useRouter();

	const { data: pokemonData } = usePokemon(name);
	const { data: pokemonTypes } = usePokemonTypes();
	const [errors, setErrors] = useState<string[]>([]);

	if (pokemonData && 'errors' in pokemonData)
		return <div className="text-center text-2xl font-bold mt-20">{(pokemonData as ErrorResponse).errors[0]}</div>;
	const [properties, setProperties] = useState<PokemonAddRequest>({
		name: '',
		types: [''],
		imageUrl: '',
		description: '',
		attack: -1,
		defense: -1,
		health: -1,
		specialAttack: -1,
		specialDefense: -1,
		speed: -1,
	});

	useEffect(() => {
		if (pokemonData === undefined || 'errors' in pokemonData) return;

		setProperties({
			...pokemonData,
			types: pokemonData.types.map((type) => type.name) as [string, string],
		});
	}, [pokemonData]);

	if (properties === undefined) return <FullScreenLoadingSpinner />;

	function setProperty<T extends keyof PokemonAddRequest>(property: T, value: PokemonAddRequest[T]) {
		setProperties({ ...(properties as PokemonAddRequest), [property]: value });
	}

	return (
		<div className="w-1/4 my-20 flex flex-col gap-5 mx-auto">
			<ErrorList errors={errors} />
			<CustomInput
				value={properties.name}
				label="Name"
				onChange={(e) => {
					setProperty('name', e.target.value);
				}}
				placeholder="Pikachu"
				type="text"
			/>

			<div>
				<label className="label">First Type</label>
				<select
					value={properties.types[0]}
					onChange={(e) => {
						const propertyClone = { ...properties };
						propertyClone.types[0] = e.target.value;
						setProperties({ ...propertyClone });
					}}
					className="select select-accent w-full"
				>
					<option disabled selected>
						Select a type
					</option>
					{pokemonTypes &&
						pokemonTypes.map((type, index) => (
							<option disabled={properties.types.includes(type.name)} key={index}>
								{type.name}
							</option>
						))}
				</select>
			</div>

			<div>
				<label className="label">Second Type</label>
				<select
					value={properties.types[1]}
					onChange={(e) => {
						const propertyClone = { ...properties };
						propertyClone.types[1] = e.target.value;
						setProperties({ ...propertyClone });
					}}
					className="select select-accent w-full"
				>
					<option disabled selected>
						Select a type
					</option>
					{pokemonTypes &&
						pokemonTypes.map((type, index) => (
							<option disabled={properties.types.includes(type.name)} key={index}>
								{type.name}
							</option>
						))}
				</select>
			</div>

			<CustomInput
				value={properties.imageUrl}
				label="Image URL"
				onChange={(e) => {
					setProperty('imageUrl', e.target.value);
				}}
				placeholder="https://assets.pokemon.com/assets/..."
				type="text"
			/>

			<CustomInput
				value={properties.description}
				label="Description"
				onChange={(e) => {
					setProperty('description', e.target.value);
				}}
				placeholder="Pikachu is an electric-type Pokemon..."
				type="text"
			/>

			<CustomInput
				value={properties.attack.toString()}
				label="Attack"
				onChange={(e) => {
					setProperty('attack', parseInt(e.target.value));
				}}
				placeholder="Attack"
				type="number"
			/>

			<CustomInput
				value={properties.defense.toString()}
				label="Defense"
				onChange={(e) => {
					setProperty('defense', parseInt(e.target.value));
				}}
				placeholder="Defense"
				type="number"
			/>

			<CustomInput
				value={properties.health.toString()}
				label="Health"
				onChange={(e) => {
					setProperty('health', parseInt(e.target.value));
				}}
				placeholder="Health"
				type="number"
			/>

			<CustomInput
				value={properties.specialAttack.toString()}
				label="Special Attack"
				onChange={(e) => {
					setProperty('specialAttack', parseInt(e.target.value));
				}}
				placeholder="Special Attack"
				type="number"
			/>

			<CustomInput
				value={properties.specialDefense.toString()}
				label="Special Defense"
				onChange={(e) => {
					setProperty('specialDefense', parseInt(e.target.value));
				}}
				placeholder="Special Defense"
				type="number"
			/>

			<CustomInput
				value={properties.speed.toString()}
				label="Speed"
				onChange={(e) => {
					setProperty('speed', parseInt(e.target.value));
				}}
				placeholder="Speed"
				type="number"
			/>

			<button
				onClick={async () => {
					const response = await fetch(`${SERVER_URL}${POKEMON_SERVER_ENDPOINTS.EDIT}`, {
						method: 'PUT',
						headers: {
							'Content-Type': 'application/json',
						},
						body: JSON.stringify({ searchName: name, ...properties }),
					});

					if (response.status !== 200) {
						const data = (await response.json()) as ErrorResponse;
						setErrors(data.errors);
					} else {
						router.push(`/pokemon/${properties.name}`);
					}
				}}
				disabled={
					pokemonData === undefined ||
					properties.name === '' ||
					properties.types[0] === '' ||
					properties.imageUrl === '' ||
					properties.description === '' ||
					properties.attack === -1 ||
					properties.defense === -1 ||
					properties.health === -1 ||
					properties.specialAttack === -1 ||
					properties.specialDefense === -1 ||
					properties.speed === -1 ||
					(properties.name === pokemonData.name &&
						properties.attack === pokemonData.attack &&
						properties.defense === pokemonData.defense &&
						properties.health === pokemonData.health &&
						properties.specialAttack === pokemonData.specialAttack &&
						properties.specialDefense === pokemonData.specialDefense &&
						properties.speed === pokemonData.speed &&
						pokemonData.types.map((type) => type.name).includes(properties.types[0]) &&
						pokemonData.types.length === 2 &&
						properties.types.length === 2 &&
						pokemonData.types.map((type) => type.name).includes(properties.types[1]))
				}
				className="btn btn-accent w-full mt-5"
			>
				Edit
			</button>
		</div>
	);
}

Edit.Auth = Admin;

export const getServerSideProps: GetServerSideProps<{
	name: string;
}> = async ({ query }) => {
	return { props: { name: query.name as string } };
};
