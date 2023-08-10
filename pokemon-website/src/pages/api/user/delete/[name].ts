import { DELETE_METHOD_WITH_PATH_QUERY_NAME } from '#/endpoints/RequestFunctionsToBackend';
import { USER_BACKEND_ENDPOINTS } from '#/endpoints/User';
import { sessionOptions } from '#/session/options';
import { withIronSessionApiRoute } from 'iron-session/next';

export default withIronSessionApiRoute(DELETE_METHOD_WITH_PATH_QUERY_NAME(USER_BACKEND_ENDPOINTS.DELETE), sessionOptions);
