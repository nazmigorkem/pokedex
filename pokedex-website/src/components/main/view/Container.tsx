import { UserHeartbeatResponse } from '#/Types/User';
import { USER_SERVER_ENDPOINTS, useHeartBeat } from '#/endpoints/User';
import { createContext, useContext, useEffect } from 'react';
import { mutate } from 'swr';

export const ContainerContext = createContext<{
	heartbeatInfo: { heartbeat: UserHeartbeatResponse; error: any; isLoading: boolean };
}>({
	heartbeatInfo: {
		heartbeat: { username: '', roles: [] },
		error: undefined,
		isLoading: true,
	},
});

export function useContainerContext() {
	return useContext(ContainerContext);
}

export default function Container({ children }: { children?: JSX.Element[] | JSX.Element }) {
	const { data, error, isLoading } = useHeartBeat();

	useEffect(() => {
		mutate(USER_SERVER_ENDPOINTS.HEARTBEAT, undefined);
	}, []);

	if (isLoading || data === undefined)
		return (
			<div className="w-full flex justify-center items-center min-h-screen bg-neutral">
				<div className="loading loading-lg"></div>
			</div>
		);
	return (
		<ContainerContext.Provider
			value={{
				heartbeatInfo: {
					heartbeat: data,
					error,
					isLoading,
				},
			}}
		>
			{children}
		</ContainerContext.Provider>
	);
}
