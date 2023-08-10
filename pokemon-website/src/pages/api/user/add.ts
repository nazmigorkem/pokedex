import { POST_METHOD } from '#/endpoints/RequestFunctionsToBackend';
import { USER_BACKEND_ENDPOINTS } from '#/endpoints/User';
import { sessionOptions } from '#/session/options';
import { withIronSessionApiRoute } from 'iron-session/next';

export default withIronSessionApiRoute(POST_METHOD(USER_BACKEND_ENDPOINTS.ADD), sessionOptions);
