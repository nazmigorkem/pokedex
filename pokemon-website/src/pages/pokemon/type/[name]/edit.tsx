import { ErrorResponse } from '#/Types/ErrorResponse';
import Admin from '#/components/main/session/auth/Admin';
import CustomInput from '#/components/main/view/CustomInput';
import ErrorList from '#/components/main/view/ErrorList';
import FullScreenLoadingSpinner from '#/components/main/view/FullScreenLoadingSpinner';
import { SERVER_URL } from '#/endpoints/Fetcher';
import { POKEMON_SERVER_ENDPOINTS, usePokemonType } from '#/endpoints/Pokemon';
import { GetServerSideProps } from 'next';
import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';
import { CirclePicker } from 'react-color';

export default function Add({ name }: { name: string }) {
	const router = useRouter();

	const [properties, setProperties] = useState<PokemonTypeEditRequest>({
		searchName: '',
		name: '',
		color: '',
	});

	const { data: pokemonType } = usePokemonType(name);
	const [errors, setErrors] = useState<string[]>([]);

	if (pokemonType && 'errors' in pokemonType) {
		return <div className="text-center text-2xl font-bold mt-20">{(pokemonType as ErrorResponse).errors[0]}</div>;
	}

	useEffect(() => {
		if (pokemonType === undefined || 'errors' in pokemonType) return;

		setProperties({
			searchName: name,
			...pokemonType,
			name: undefined,
		} as PokemonTypeEditRequest);
	}, [pokemonType]);

	function setProperty<T extends keyof typeof properties>(property: T, value: (typeof properties)[T]) {
		setProperties({ ...properties, [property]: value });
	}

	if (properties === undefined) return <FullScreenLoadingSpinner />;

	return (
		<div className="w-1/4 my-20 flex flex-col gap-5 mx-auto">
			<ErrorList errors={errors} />
			<CustomInput
				value={properties.name ?? name}
				type="text"
				placeholder="Type Name"
				maxLength={255}
				label="Type Name"
				onChange={(e) => {
					if (e.target.value !== pokemonType?.name) {
						setProperty('name', e.target.value);
					} else {
						setProperty('name', undefined);
					}
				}}
			/>

			<div className="flex flex-col gap-2">
				<label>Color</label>
				<CirclePicker
					color={properties.color}
					onChange={(e) => {
						setProperty('color', e.hex);
					}}
				/>
			</div>
			<button
				onClick={async () => {
					const response = await fetch(`${SERVER_URL}${POKEMON_SERVER_ENDPOINTS.TYPES.EDIT}`, {
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
						router.push(`/`);
					}
				}}
				disabled={pokemonType === undefined || (properties.name === undefined && properties.color === pokemonType.color)}
				className="btn btn-accent w-full mt-5"
			>
				Edit
			</button>
		</div>
	);
}

Add.Auth = Admin;

export const getServerSideProps: GetServerSideProps<{
	name: string;
}> = async ({ query }) => {
	return { props: { name: query.name as string } };
};
