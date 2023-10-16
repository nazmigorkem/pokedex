import { ErrorResponse } from '#/Types/ErrorResponse';
import { UserHeartbeatResponse, UserResponse } from '#/Types/User';
import useSWR from 'swr';
import { fetcher } from './Fetcher';

export const USER_SERVER_ENDPOINTS = {
	HEARTBEAT: '/api/@me/heartbeat',
	LOGIN: '/api/@me/login',
	LOGOUT: '/api/@me/logout',
	SIGNUP: '/api/@me/signup',
	ADD: '/api/user/add',
	SEARCH: '/api/user/search',
	DELETE: '/api/user/delete',
	EDIT: '/api/user/edit',

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
	SEARCH: '/user/search',
	ADD: '/user/add',
	LOGIN: '/login',
	EDIT: '/user/edit',
	DELETE: '/user/delete',

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
	const { data, error, isLoading } = useSWR<UserHeartbeatResponse>(USER_SERVER_ENDPOINTS.HEARTBEAT, fetcher, {
		refreshInterval: 1000,
	});

	return {
		data: data,
		error,
		isLoading,
	};
};

export const useUser = (name: string) => {
	const { data, error, isLoading } = useSWR<UserResponse | ErrorResponse>(`${USER_SERVER_ENDPOINTS.SEARCH}/${name}`, fetcher);

	return {
		data: data,
		error,
		isLoading,
	};
};
