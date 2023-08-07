import { sessionOptions } from '#/session/options';
import { withIronSessionApiRoute } from 'iron-session/next';
import { NextApiRequest, NextApiResponse } from 'next';
import cookie from 'cookie';
import { BACKEND_USER_GET_ENDPOINT } from '#/endpoints/User';
import { BACKEND_URL } from '#/endpoints/Fetcher';

export default withIronSessionApiRoute(handle, sessionOptions);

async function handle(req: NextApiRequest, res: NextApiResponse) {
	if (!req.session.user?.JSESSIONID) {
		return res.status(401).send({ username: undefined });
	}

	const response = await fetch(`${BACKEND_URL}${BACKEND_USER_GET_ENDPOINT}/${req.session.user.username}`, {
		method: 'GET',
		headers: {
			Cookie: cookie.serialize('JSESSIONID', req.session.user.JSESSIONID),
		},
	});

	if (!response.ok) {
		return res.status(response.status).send({ username: undefined });
	}

	res.status(200).json({ username: req.session.user.username });
}
