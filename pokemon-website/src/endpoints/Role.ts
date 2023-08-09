import { RoleResponse } from '#/Types/User';
import useSWR from 'swr';
import { fetcher } from './Fetcher';

export const ROLE_SERVER_ENDPOINTS = {
	SEARCH: '/api/role/search',
};

export const ROLE_BACKEND_ENDPOINTS = {
	SEARCH: '/role/search',
};

export const useRoles = () => {
	const { data, error, isLoading } = useSWR<RoleResponse[]>(ROLE_SERVER_ENDPOINTS.SEARCH, fetcher);

	return {
		data: data,
		error,
		isLoading,
	};
};
