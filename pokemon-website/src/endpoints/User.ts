import useSWRImmutable from 'swr/immutable';
import { fetcher } from './Fetcher';

export const HEARTBEAT_ENDPOINT = '/api/@me/heartbeat';
export const LOGIN_ENDPOINT = '/api/@me/login';
export const LOGOUT_ENDPOINT = '/api/@me/logout';
export const SIGNUP_ENDPOINT = '/api/@me/signup';
export const BASE_BACKEND_URL = 'http://localhost:8080';
export const BASE_SERVER_URL = 'http://localhost:3000';

export const useHeartBeat = () => {
	const { data, error, isLoading } = useSWRImmutable(HEARTBEAT_ENDPOINT, fetcher, {
		revalidateOnMount: false,
		revalidateOnFocus: false,
		revalidateOnReconnect: false,
	});

	return {
		data: data as { username: string } | undefined,
		error,
		isLoading,
	};
};
