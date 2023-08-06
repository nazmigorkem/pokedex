import { useContext, createContext, useState, Dispatch, SetStateAction } from 'react';

type Heartbeat = { username: string } | undefined;

const ContainerContext = createContext<{ heartbeat: Heartbeat; setHeartbeat: Dispatch<SetStateAction<Heartbeat>> }>({
	heartbeat: undefined,
	setHeartbeat: () => {},
});

export function useContainerContext() {
	return useContext(ContainerContext);
}

export default function Context({ children }: { children: React.ReactNode }) {
	const [heartbeat, setHeartbeat] = useState(undefined as Heartbeat);

	return <ContainerContext.Provider value={{ heartbeat, setHeartbeat }}>{children}</ContainerContext.Provider>;
}
