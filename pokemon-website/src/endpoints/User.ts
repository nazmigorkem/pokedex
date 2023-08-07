import useSWRImmutable from 'swr/immutable';
import { fetcher } from './Fetcher';

export const SERVER_HEARTBEAT_ENDPOINT = '/api/@me/heartbeat';
export const SERVER_LOGIN_ENDPOINT = '/api/@me/login';
export const SERVER_LOGOUT_ENDPOINT = '/api/@me/logout';
export const SERVER_SIGNUP_ENDPOINT = '/api/@me/signup';

export const SERVER_USER_CATCH_LIST_ENDPOINT = '/api/user/catch-list';
export const SERVER_USER_WISH_LIST_ENDPOINT = '/api/user/wish-list';

export const BACKEND_GET_USER_CATCH_LIST_ENDPOINT = '/user/catch-list';
export const BACKEND_GET_USER_WISH_LIST_ENDPOINT = '/user/wish-list';

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
