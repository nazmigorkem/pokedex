import { useHeartBeat } from '#/endpoints/User';
import { useContext } from 'react';
import { createContext } from 'react';

const ContainerContext = createContext<{ heartbeat: { username: string } | undefined }>({ heartbeat: undefined });

export function useContainerContext() {
	return useContext(ContainerContext);
}

export default function Context({ children }: { children: React.ReactNode }) {
	const { data, error, isLoading } = useHeartBeat();
	console.log('data', data);

	return <ContainerContext.Provider value={{ heartbeat: data }}>{children}</ContainerContext.Provider>;
}
