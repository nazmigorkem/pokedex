import { POKEMON_BACKEND_ENDPOINTS } from '#/endpoints/Pokemon';
import { GET_METHOD_WITH_SEARCH_SLUG } from '#/endpoints/RequestFunctionsToBackend';
import { sessionOptions } from '#/session/options';
import { withIronSessionApiRoute } from 'iron-session/next';

export default withIronSessionApiRoute(GET_METHOD_WITH_SEARCH_SLUG(POKEMON_BACKEND_ENDPOINTS.SEARCH), sessionOptions);
