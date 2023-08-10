import { POKEMON_BACKEND_ENDPOINTS } from '#/endpoints/Pokemon';
import { POST_METHOD } from '#/endpoints/RequestFunctionsToBackend';
import { sessionOptions } from '#/session/options';
import { withIronSessionApiRoute } from 'iron-session/next';

export default withIronSessionApiRoute(POST_METHOD(POKEMON_BACKEND_ENDPOINTS.ADD), sessionOptions);
