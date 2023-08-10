import { ErrorResponse } from '#/Types/ErrorResponse';
import useSWR from 'swr';
import { fetcher } from './Fetcher';

export const POKEMON_BACKEND_ENDPOINTS = {
	SEARCH: '/pokemon/search',
	ADD: '/pokemon/add',
	DELETE: '/pokemon/delete',
	EDIT: '/pokemon/edit',

	TYPES: {
		SEARCH: '/pokemon-type/search',
		ADD: '/pokemon-type/add',
		EDIT: '/pokemon-type/edit',
	},
} as const;

export const POKEMON_SERVER_ENDPOINTS = {
	SEARCH: '/api/pokemon/search',
	ADD: '/api/pokemon/add',
	DELETE: '/api/pokemon/delete',
	EDIT: '/api/pokemon/edit',

	TYPES: {
		SEARCH: '/api/pokemon/types/search',
		ADD: '/api/pokemon/types/add',
		EDIT: '/api/pokemon/types/edit',
	},
} as const;

export const usePokemonTypes = (name?: string) => {
	const query = new URLSearchParams({
		name: name ?? '',
	});

	const { data, error, isLoading } = useSWR<PokemonTypeResponse[]>(`${POKEMON_SERVER_ENDPOINTS.TYPES.SEARCH}?${query}`, fetcher);

	return {
		data: data,
		error,
		isLoading,
	};
};

export const usePokemonType = (name: string) => {
	const { data, error, isLoading } = useSWR<PokemonTypeResponse | ErrorResponse>(`${POKEMON_SERVER_ENDPOINTS.TYPES.SEARCH}/${name}`, fetcher);

	return {
		data: data,
		error,
		isLoading,
	};
};

export const usePokemon = (name: string) => {
	const { data, error, isLoading } = useSWR<PokemonResponse | ErrorResponse>(`${POKEMON_SERVER_ENDPOINTS.SEARCH}/${name}`, fetcher);

	return {
		data: data,
		error,
		isLoading,
	};
};
