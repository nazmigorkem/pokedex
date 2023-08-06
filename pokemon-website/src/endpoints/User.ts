import useSWRImmutable from 'swr/immutable';
import { fetcher } from './Fetcher';

export const BACKEND_URL = 'http://localhost:8080/api';
export const SERVER_URL = 'http://localhost:3000';

export const SERVER_HEARTBEAT_ENDPOINT = '/api/@me/heartbeat';
export const SERVER_LOGIN_ENDPOINT = '/api/@me/login';
export const SERVER_LOGOUT_ENDPOINT = '/api/@me/logout';
export const SERVER_SIGNUP_ENDPOINT = '/api/@me/signup';

export const BACKEND_USER_GET_ENDPOINT = '/user';
export const BACKEND_USER_ADD_ENDPOINT = '/user/add';
export const BACKEND_USER_LOGIN_ENDPOINT = '/login';

export const useHeartBeat = () => {
	const { data, error, isLoading } = useSWRImmutable(SERVER_HEARTBEAT_ENDPOINT, fetcher, {
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
