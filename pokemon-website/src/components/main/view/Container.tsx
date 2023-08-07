import { USER_SERVER_ENDPOINTS, useHeartBeat } from '#/endpoints/User';
import { createContext, useContext, useEffect } from 'react';
import { mutate } from 'swr';

type Heartbeat = { username: string } | undefined;

export const ContainerContext = createContext<{
	heartbeatInfo: { heartbeat: Heartbeat; error: any; isLoading: boolean };
}>({
	heartbeatInfo: {
		heartbeat: undefined,
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
