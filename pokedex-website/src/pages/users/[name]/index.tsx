import { ErrorResponse } from '#/Types/ErrorResponse';
import { UserResponse, hasRoles } from '#/Types/User';
import Admin from '#/components/main/session/auth/Admin';
import { useContainerContext } from '#/components/main/view/Container';
import FullScreenLoadingSpinner from '#/components/main/view/FullScreenLoadingSpinner';
import { SERVER_URL, fetcher } from '#/endpoints/Fetcher';
import { USER_SERVER_ENDPOINTS } from '#/endpoints/User';
import { GetServerSideProps } from 'next';
import { useRouter } from 'next/router';
import { useState } from 'react';
import useSWR from 'swr';

export default function Name({ name }: { name: string }) {
	const { heartbeatInfo } = useContainerContext();
	const router = useRouter();

	const [errors, setErrors] = useState<string[]>([]);
	const { data: userData, error, isLoading } = useSWR<UserResponse | ErrorResponse>(`${USER_SERVER_ENDPOINTS.SEARCH}/${name}`, fetcher);
	if (userData && 'errors' in userData) return <div className="text-center text-2xl font-bold mt-20">{userData.errors[0]}</div>;

	if (isLoading || userData === undefined) {
		return <FullScreenLoadingSpinner />;
	}

	return (
		<div className="m-20 flex flex-col gap-5">
			<div className="flex justify-between transition-none">
				<h1 className="text-5xl font-bold">{name}</h1>
				{hasRoles(heartbeatInfo.heartbeat, ['ROLE_ADMIN']) && (
					<div className="ml-auto flex gap-3">
						<button
							onClick={async () => {
								router.push(`/users/${name}/edit`);
							}}
							className="btn btn-neutral btn-square btn-outline"
						>
							<i className="fas fa-pen" />
						</button>
						<button
							onClick={async () => {
								const response = await fetch(`${SERVER_URL}${USER_SERVER_ENDPOINTS.DELETE}/${name}`, {
									method: 'DELETE',
								});

								if (response.status === 200) {
									router.replace('/users');
								} else {
									setErrors((await response.json()).errors);
								}
							}}
							className="btn btn-error btn-square btn-outline"
						>
							<i className="fas fa-trash" />
						</button>
					</div>
				)}
			</div>
			<div className="flex flex-col gap-3">
				<h1 className="text-3xl">Roles</h1>
				<div className="grid grid-cols-5 gap-5 text-center font-bold">
					{userData.roles.map((role) => {
						return <div className="bg-primary text-primary-content rounded-md p-3 select-none col-span-1">{role.name}</div>;
					})}
				</div>
			</div>
		</div>
	);
}

Name.Auth = Admin;

export const getServerSideProps: GetServerSideProps<{
	name: string;
}> = async ({ query }) => {
	return { props: { name: query.name as string } };
};
