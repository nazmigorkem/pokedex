import { BACKEND_URL } from '#/endpoints/Fetcher';
import { USER_BACKEND_ENDPOINTS } from '#/endpoints/User';
import { sessionOptions } from '#/session/options';
import cookie from 'cookie';
import { withIronSessionApiRoute } from 'iron-session/next';
import { NextApiRequest, NextApiResponse } from 'next';

export default withIronSessionApiRoute(handle, sessionOptions);

async function handle(req: NextApiRequest, res: NextApiResponse) {
	if (!req.session.user?.JSESSIONID) {
		return res.status(401).send({ username: undefined });
	}

	if (req.method !== 'POST') {
		return res.status(405).send({ errors: ['Method not allowed.'] });
	}

	const response = await fetch(`${BACKEND_URL}${USER_BACKEND_ENDPOINTS.WISH_LIST.DELETE}`, {
		headers: {
			Cookie: cookie.serialize('JSESSIONID', req.session.user.JSESSIONID),
			'Content-Type': 'application/json',
		},
		method: 'POST',
		body: JSON.stringify({ ...req.body, username: req.session.user.username }),
	});

	if (!response.ok) {
		return res.status(response.status).send({ errors: ['Failed to fetch.'] });
	}

	const data = await response.json();

	res.status(200).json(data);
}
