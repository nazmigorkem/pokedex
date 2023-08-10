import { POKEMON_BACKEND_ENDPOINTS } from '#/endpoints/Pokemon';
import { REQUEST_WITH_JSON_BODY } from '#/endpoints/RequestFunctionsToBackend';
import { sessionOptions } from '#/session/options';
import { withIronSessionApiRoute } from 'iron-session/next';

export default withIronSessionApiRoute(REQUEST_WITH_JSON_BODY(POKEMON_BACKEND_ENDPOINTS.ADD, 'POST'), sessionOptions);
