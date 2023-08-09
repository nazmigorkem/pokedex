import { ErrorResponse } from '#/Types/ErrorResponse';
import { UserResponse, hasRoles } from '#/Types/User';
import { useContainerContext } from '#/components/main/view/Container';
import { fetcher } from '#/endpoints/Fetcher';
import { USER_SERVER_ENDPOINTS } from '#/endpoints/User';
import { GetServerSideProps } from 'next';
import { useRouter } from 'next/router';
import useSWR from 'swr';

export default function Name({ name }: { name: string }) {
	const { heartbeatInfo } = useContainerContext();
	const router = useRouter();

	if (!hasRoles(heartbeatInfo.heartbeat, ['ROLE_ADMIN'])) {
		router.push('/');
		return <></>;
	}

	const { data: userData, error, isLoading } = useSWR<UserResponse | ErrorResponse>(`${USER_SERVER_ENDPOINTS.SEARCH}/${name}`, fetcher);
	if (userData && 'errors' in userData) return <div className="text-center text-2xl font-bold mt-20">{userData.errors[0]}</div>;

	if (isLoading || userData === undefined) {
		return (
			<div className="w-full min-h-screen flex items-center justify-center">
				<div className="loading loading-lg"></div>
			</div>
		);
	}

	return (
		<div className="m-20 flex flex-col gap-5">
			<div className="flex justify-between transition-none">
				<h1 className="text-5xl font-bold">{name}</h1>
				<div className="ml-auto">
					<button
						onClick={async () => {
							const res = await fetch(`${USER_SERVER_ENDPOINTS.DELETE}/${name}`, {
								method: 'DELETE',
							});
							if (res.status === 200) {
								router.replace('/users');
							}
						}}
						className="btn btn-error btn-square btn-ghost btn-outline"
					>
						<i className="fas fa-trash" />
					</button>
				</div>
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

export const getServerSideProps: GetServerSideProps<{
	name: string;
}> = async ({ query }) => {
	return { props: { name: query.name as string } };
};
