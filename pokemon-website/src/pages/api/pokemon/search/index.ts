import { POKEMON_BACKEND_ENDPOINTS } from '#/endpoints/Pokemon';
import { GET_METHOD_WITH_PAGE_QUERIES } from '#/endpoints/RequestFunctionsToBackend';
import { sessionOptions } from '#/session/options';
import { withIronSessionApiRoute } from 'iron-session/next';

export default withIronSessionApiRoute(GET_METHOD_WITH_PAGE_QUERIES(POKEMON_BACKEND_ENDPOINTS.SEARCH), sessionOptions);
