import { ErrorResponse } from '#/Types/ErrorResponse';
import { UserAddRequest } from '#/Types/User';
import Admin from '#/components/main/session/auth/Admin';
import CustomInput from '#/components/main/view/CustomInput';
import ErrorList from '#/components/main/view/ErrorList';
import { SERVER_URL } from '#/endpoints/Fetcher';
import { useRoles } from '#/endpoints/Role';
import { USER_SERVER_ENDPOINTS } from '#/endpoints/User';
import { useRouter } from 'next/router';
import { useState } from 'react';

export default function Add() {
	const router = useRouter();

	const [properties, setProperties] = useState<UserAddRequest>({
		username: '',
		password: '',
		roles: [],
	});

	const [passwordConfirmation, setPasswordConfirmation] = useState('');

	const [errors, setErrors] = useState<string[]>([]);

	const { data: roles } = useRoles();

	function setProperty<T extends keyof typeof properties>(property: T, value: (typeof properties)[T]) {
		setProperties({ ...properties, [property]: value });
	}

	return (
		<div className="w-1/4 my-20 flex flex-col gap-5 mx-auto">
			<ErrorList errors={errors} />
			<CustomInput
				label="Username"
				onChange={(e) => {
					setProperty('username', e.target.value);
				}}
				placeholder="Username"
				type="text"
			/>

			{/*  */}

			<CustomInput
				label="Password"
				onChange={(e) => {
					setProperty('password', e.target.value);
					const passwordsNotSameString = 'Passwords are not same.';
					if (errors.includes(passwordsNotSameString) && e.target.value === passwordConfirmation) {
						setErrors(errors.filter((error) => error !== passwordsNotSameString));
					} else if (e.target.value !== passwordConfirmation && !errors.includes(passwordsNotSameString)) {
						setErrors([...errors, passwordsNotSameString]);
					}
				}}
				placeholder="Password"
				type="password"
			/>

			<CustomInput
				label="Confirm Password"
				onChange={(e) => {
					setPasswordConfirmation(e.target.value);

					const passwordsNotSameString = 'Passwords are not same.';
					if (errors.includes(passwordsNotSameString) && properties.password === e.target.value) {
						setErrors(errors.filter((error) => error !== passwordsNotSameString));
					} else if (properties.password !== e.target.value && !errors.includes(passwordsNotSameString)) {
						setErrors([...errors, passwordsNotSameString]);
					}
				}}
				placeholder="Password"
				type="password"
			/>

			<button
				onClick={async () => {
					const response = await fetch(`${SERVER_URL}${USER_SERVER_ENDPOINTS.ADD}`, {
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
						router.push(`/users/${properties.username}`);
					}
				}}
				disabled={
					properties.username === '' ||
					properties.roles.length === 0 ||
					properties.password === '' ||
					properties.password !== passwordConfirmation
				}
				className="btn btn-accent w-full mt-5"
			>
				Add
			</button>
		</div>
	);
}

Add.Auth = Admin;
