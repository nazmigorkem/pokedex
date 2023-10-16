import { ErrorResponse } from '#/Types/ErrorResponse';
import Admin from '#/components/main/session/auth/Admin';
import CustomInput from '#/components/main/view/CustomInput';
import ErrorList from '#/components/main/view/ErrorList';
import { SERVER_URL } from '#/endpoints/Fetcher';
import { POKEMON_SERVER_ENDPOINTS } from '#/endpoints/Pokemon';
import { useRouter } from 'next/router';
import { useState } from 'react';
import { CirclePicker } from 'react-color';

export default function Add() {
	const router = useRouter();

	const [properties, setProperties] = useState<PokemonTypeAddRequest>({
		name: '',
		color: '',
	});

	const [errors, setErrors] = useState<string[]>([]);

	function setProperty<T extends keyof typeof properties>(property: T, value: (typeof properties)[T]) {
		setProperties({ ...properties, [property]: value });
	}

	return (
		<div className="w-1/4 my-20 flex flex-col gap-5 mx-auto">
			<ErrorList errors={errors} />
			<CustomInput
				type="text"
				placeholder="Type Name"
				maxLength={255}
				label="Type Name"
				onChange={(e) => {
					setProperty('name', e.target.value);
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
					const response = await fetch(`${SERVER_URL}${POKEMON_SERVER_ENDPOINTS.TYPES.ADD}`, {
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
				disabled={false}
				className="btn btn-accent w-full mt-5"
			>
				Add
			</button>
		</div>
	);
}

Add.Auth = Admin;
