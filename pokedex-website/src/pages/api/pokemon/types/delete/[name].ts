import { POKEMON_BACKEND_ENDPOINTS } from '#/endpoints/Pokemon';
import { DELETE_METHOD_WITH_PATH_QUERY_NAME } from '#/endpoints/RequestFunctionsToBackend';
import { sessionOptions } from '#/session/options';
import { withIronSessionApiRoute } from 'iron-session/next';

export default withIronSessionApiRoute(DELETE_METHOD_WITH_PATH_QUERY_NAME(POKEMON_BACKEND_ENDPOINTS.TYPES.DELETE), sessionOptions);
