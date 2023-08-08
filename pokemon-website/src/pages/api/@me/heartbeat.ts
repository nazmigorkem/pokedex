import { sessionOptions } from '#/session/options';
import { withIronSessionApiRoute } from 'iron-session/next';
import { NextApiRequest, NextApiResponse } from 'next';
import cookie from 'cookie';
import { BACKEND_URL } from '#/endpoints/Fetcher';
import { USER_BACKEND_ENDPOINTS } from '#/endpoints/User';
import { ROLES } from '#/Types/UserResponse';

export default withIronSessionApiRoute(handle, sessionOptions);

async function handle(req: NextApiRequest, res: NextApiResponse) {
	if (!req.session.user?.JSESSIONID) {
		return res.status(401).send({ username: '', roles: [ROLES.anonymous] });
	}

	const response = await fetch(`${BACKEND_URL}${USER_BACKEND_ENDPOINTS.GET}/${req.session.user.username}/heartbeat`, {
		method: 'GET',
		headers: {
			Cookie: cookie.serialize('JSESSIONID', req.session.user.JSESSIONID),
		},
	});

	if (!response.ok) {
		return res.status(response.status).send({ username: '', roles: [ROLES.anonymous] });
	}

	res.status(200).json(await response.json());
}
