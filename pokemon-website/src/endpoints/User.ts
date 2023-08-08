import useSWRImmutable from 'swr/immutable';
import { fetcher } from './Fetcher';
import { UserHeartbeatResponse } from '#/Types/UserResponse';

export const USER_SERVER_ENDPOINTS = {
	HEARTBEAT: '/api/@me/heartbeat',
	LOGIN: '/api/@me/login',
	LOGOUT: '/api/@me/logout',
	SIGNUP: '/api/@me/signup',

	CATCH_LIST: {
		GET: '/api/user/list/catch',
		ADD: '/api/user/list/catch/add',
		DELETE: '/api/user/list/catch/delete',
		IS_EXIST: '/api/user/list/catch/is-exists',
	},
	WISH_LIST: {
		GET: '/api/user/list/wish',
		ADD: '/api/user/list/wish/add',
		DELETE: '/api/user/list/wish/delete',
		IS_EXIST: '/api/user/list/wish/is-exists',
	},
} as const;

export const USER_BACKEND_ENDPOINTS = {
	GET: '/user',
	ADD: '/user/add',
	LOGIN: '/login',

	CATCH_LIST: {
		GET: '/user/list/catch',
		ADD: '/user/list/catch/add',
		DELETE: '/user/list/catch/delete',
		IS_EXIST: '/user/list/catch/is-exists',
	},
	WISH_LIST: {
		GET: '/user/list/wish',
		ADD: '/user/list/wish/add',
		DELETE: '/user/list/wish/delete',
		IS_EXIST: '/user/list/wish/is-exists',
	},
} as const;

export const useHeartBeat = () => {
	const { data, error, isLoading } = useSWRImmutable<UserHeartbeatResponse>(USER_SERVER_ENDPOINTS.HEARTBEAT, fetcher, {
		revalidateOnMount: false,
		revalidateOnFocus: false,
		revalidateOnReconnect: false,
	});

	return {
		data: data,
		error,
		isLoading,
	};
};
