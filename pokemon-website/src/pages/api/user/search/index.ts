import { GET_METHOD_WITH_PAGE_QUERIES } from '#/endpoints/RequestFunctionsToBackend';
import { USER_BACKEND_ENDPOINTS } from '#/endpoints/User';
import { sessionOptions } from '#/session/options';
import { withIronSessionApiRoute } from 'iron-session/next';

export default withIronSessionApiRoute(GET_METHOD_WITH_PAGE_QUERIES(USER_BACKEND_ENDPOINTS.SEARCH), sessionOptions);
