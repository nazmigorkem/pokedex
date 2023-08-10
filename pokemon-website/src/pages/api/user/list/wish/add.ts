import { REQUEST_WITH_JSON_BODY } from '#/endpoints/RequestFunctionsToBackend';
import { USER_BACKEND_ENDPOINTS } from '#/endpoints/User';
import { sessionOptions } from '#/session/options';
import { withIronSessionApiRoute } from 'iron-session/next';

export default withIronSessionApiRoute(REQUEST_WITH_JSON_BODY(USER_BACKEND_ENDPOINTS.WISH_LIST.ADD, 'POST'), sessionOptions);
