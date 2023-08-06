import useSWR from 'swr';
import { fetcher } from './Fetcher';

export const useHeartBeat = () => {
	const { data, error, isLoading } = useSWR('/api/@me/heartbeat', fetcher, {
		refreshInterval: 1000 * 60 * 5,
	});

	return {
		data: data as { username: string } | undefined,
		error,
		isLoading,
	};
};
