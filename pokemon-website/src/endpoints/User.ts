import useSWRImmutable from 'swr/immutable';
import { fetcher } from './Fetcher';

export const USER_SERVER_ENDPOINTS = {
	HEARTBEAT: '/api/@me/heartbeat',
	LOGIN: '/api/@me/login',
	LOGOUT: '/api/@me/logout',
	SIGNUP: '/api/@me/signup',

	CATCH_LIST: {
		GET: '/api/user/catch-list',
	},
	WISH_LIST: {
		GET: '/api/user/wish-list',
	},
} as const;

export const USER_BACKEND_ENDPOINTS = {
	GET: '/user',
	ADD: '/user/add',
	LOGIN: '/login',

	CATCH_LIST: {
		GET: '/user/catch-list',
	},
	WISH_LIST: {
		GET: '/user/wish-list',
	},
} as const;

export const useHeartBeat = () => {
	const { data, error, isLoading } = useSWRImmutable(USER_SERVER_ENDPOINTS.HEARTBEAT, fetcher, {
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
