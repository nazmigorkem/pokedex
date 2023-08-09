import useSWR from 'swr';
import { fetcher } from './Fetcher';

export const POKEMON_BACKEND_ENDPOINTS = {
	SEARCH: '/pokemon/search',
	ADD: '/pokemon/add',
	DELETE: '/pokemon/delete',
	TYPES: {
		GET: '/pokemon-type/search',
	},
} as const;

export const POKEMON_SERVER_ENDPOINTS = {
	SEARCH: '/api/pokemon/search',
	ADD: '/api/pokemon/add',
	DELETE: '/api/pokemon/delete',
	TYPES: {
		GET: '/api/pokemon/types/search',
	},
} as const;

export const usePokemonTypes = () => {
	const { data, error, isLoading } = useSWR<PokemonTypeResponse[]>(POKEMON_SERVER_ENDPOINTS.TYPES.GET, fetcher);

	return {
		data: data,
		error,
		isLoading,
	};
};
