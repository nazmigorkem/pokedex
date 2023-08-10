import { ErrorResponse } from '#/Types/ErrorResponse';
import { UserEditRequest } from '#/Types/User';
import Admin from '#/components/main/session/auth/Admin';
import CustomInput from '#/components/main/view/CustomInput';
import ErrorList from '#/components/main/view/ErrorList';
import FullScreenLoadingSpinner from '#/components/main/view/FullScreenLoadingSpinner';
import RoleSelection from '#/components/user/RoleSelection';
import { SERVER_URL } from '#/endpoints/Fetcher';
import { useRoles } from '#/endpoints/Role';
import { USER_SERVER_ENDPOINTS, useUser } from '#/endpoints/User';
import { GetServerSideProps } from 'next';
import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';

export default function Edit({ name }: { name: string }) {
	const router = useRouter();

	const { data: userData } = useUser(name);
	const { data: roles } = useRoles();
	const [errors, setErrors] = useState<string[]>([]);
	if (userData && 'errors' in userData) return <div className="text-center text-2xl font-bold mt-20">{(userData as ErrorResponse).errors[0]}</div>;
	const [properties, setProperties] = useState<UserEditRequest>({
		searchUsername: name,
		roles: [{ name: 'ROLE_ANONYMOUS' }],
		catchList: [],
		wishlist: [],
	});

	useEffect(() => {
		if (userData === undefined || 'errors' in userData) return;

		setProperties({
			searchUsername: name,
			...userData,
			username: undefined,
			password: undefined,
		} as UserEditRequest);
	}, [userData]);

	if (properties === undefined) return <FullScreenLoadingSpinner />;

	return (
		<div className="w-1/4 my-20 flex flex-col gap-5 mx-auto">
			<ErrorList errors={errors} />
			<CustomInput
				value={properties.username ?? userData?.username}
				label="Username"
				placeholder="Username"
				type="text"
				onChange={(e) => {
					if (e.target.value !== userData?.username) {
						setProperties({
							...properties,
							username: e.target.value,
						});
					} else {
						setProperties({
							...properties,
							username: undefined,
						});
					}
				}}
			/>
			<CustomInput
				value={properties.password}
				label="Password"
				placeholder="Password"
				type="password"
				onChange={(e) => {
					setProperties({
						...properties,
						password: e.target.value,
					});
				}}
			/>
			<RoleSelection properties={properties} setProperties={setProperties} roles={roles} />
			<button
				disabled={
					properties.roles[0].name === 'ROLE_ANONYMOUS' ||
					(properties.username === undefined &&
						properties.roles.every((x) => userData?.roles.some((y) => y.name === x.name)) &&
						properties.password === undefined)
				}
				onClick={async () => {
					const response = await fetch(`${SERVER_URL}${USER_SERVER_ENDPOINTS.EDIT}`, {
						method: 'PUT',
						headers: {
							'Content-Type': 'application/json',
						},
						body: JSON.stringify(properties),
					});

					if (response.status !== 200) {
						const data = (await response.json()) as ErrorResponse;
						setErrors(data.errors);
					} else {
						router.push(`/users/${properties.username ?? name}`);
					}
				}}
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
