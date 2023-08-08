import { ErrorResponse } from '#/Types/ErrorResponse';
import { hasRoles } from '#/Types/UserResponse';
import { useContainerContext } from '#/components/main/view/Container';
import CustomInput from '#/components/main/view/CustomInput';
import ErrorList from '#/components/main/view/ErrorList';
import { SERVER_URL } from '#/endpoints/Fetcher';
import { POKEMON_SERVER_ENDPOINTS, usePokemonTypes } from '#/endpoints/Pokemon';
import { useRouter } from 'next/router';
import { useState } from 'react';

export default function Add() {
	const context = useContainerContext();
	const heartbeatInfo = context.heartbeatInfo;
	const router = useRouter();

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

	const [errors, setErrors] = useState<string[]>([]);

	const { data: pokemonTypes } = usePokemonTypes();

	function setProperty<T extends keyof typeof properties>(property: T, value: (typeof properties)[T]) {
		setProperties({ ...properties, [property]: value });
	}

	if (!hasRoles(heartbeatInfo.heartbeat, ['ROLE_ADMIN'])) {
		router.push('/');
		return <></>;
	}

	return (
		<div className="w-1/4 my-20 flex flex-col gap-5 mx-auto">
			{errors.length !== 0 && <ErrorList errors={errors} />}

			<CustomInput
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
				label="Image URL"
				onChange={(e) => {
					setProperty('imageUrl', e.target.value);
				}}
				placeholder="https://assets.pokemon.com/assets/..."
				type="text"
			/>

			<CustomInput
				label="Description"
				onChange={(e) => {
					setProperty('description', e.target.value);
				}}
				placeholder="Pikachu is an electric-type Pokemon..."
				type="text"
			/>

			<CustomInput
				label="Attack"
				onChange={(e) => {
					setProperty('attack', parseInt(e.target.value));
				}}
				placeholder="Attack"
				type="number"
			/>

			<CustomInput
				label="Defense"
				onChange={(e) => {
					setProperty('defense', parseInt(e.target.value));
				}}
				placeholder="Defense"
				type="number"
			/>

			<CustomInput
				label="Health"
				onChange={(e) => {
					setProperty('health', parseInt(e.target.value));
				}}
				placeholder="Health"
				type="number"
			/>

			<CustomInput
				label="Special Attack"
				onChange={(e) => {
					setProperty('specialAttack', parseInt(e.target.value));
				}}
				placeholder="Special Attack"
				type="number"
			/>

			<CustomInput
				label="Special Defense"
				onChange={(e) => {
					setProperty('specialDefense', parseInt(e.target.value));
				}}
				placeholder="Special Defense"
				type="number"
			/>

			<CustomInput
				label="Speed"
				onChange={(e) => {
					setProperty('speed', parseInt(e.target.value));
				}}
				placeholder="Speed"
				type="number"
			/>

			<button
				onClick={async () => {
					const response = await fetch(`${SERVER_URL}${POKEMON_SERVER_ENDPOINTS.ADD}`, {
						method: 'POST',
						headers: {
							'Content-Type': 'application/json',
						},
						body: JSON.stringify(properties),
					});

					if (response.status !== 200) {
						const data = (await response.json()) as ErrorResponse;
						setErrors(data.errors);
					} else {
						router.push(`/pokemon/${properties.name}`);
					}
				}}
				disabled={
					properties.name === '' ||
					properties.types[0] === '' ||
					properties.imageUrl === '' ||
					properties.description === '' ||
					properties.attack === -1 ||
					properties.defense === -1 ||
					properties.health === -1 ||
					properties.specialAttack === -1 ||
					properties.specialDefense === -1 ||
					properties.speed === -1
				}
				className="btn btn-accent w-full mt-5"
			>
				Add
			</button>
		</div>
	);
}
