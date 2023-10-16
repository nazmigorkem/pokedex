import { GET_METHOD_WITH_PAGE_QUERIES } from '#/endpoints/RequestFunctionsToBackend';
import { ROLE_BACKEND_ENDPOINTS } from '#/endpoints/Role';
import { sessionOptions } from '#/session/options';
import { withIronSessionApiRoute } from 'iron-session/next';

export default withIronSessionApiRoute(GET_METHOD_WITH_PAGE_QUERIES(ROLE_BACKEND_ENDPOINTS.SEARCH), sessionOptions);
