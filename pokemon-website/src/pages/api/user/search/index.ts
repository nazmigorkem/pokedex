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

	const { pageNumber, pageSize, name } = req.query;

	const query = new URLSearchParams({
		pageNumber: pageNumber as string,
		pageSize: pageSize as string,
		name: name as string,
	});

	const response = await fetch(`${BACKEND_URL}${USER_BACKEND_ENDPOINTS.SEARCH}?${query}`, {
		headers: {
			Cookie: cookie.serialize('JSESSIONID', req.session.user.JSESSIONID),
		},
	});

	if (!response.ok) {
		return res.status(response.status).send(response.status === 400 ? await response.json() : { errors: ['Failed to fetch.'] });
	}

	const data = await response.json();

	res.status(200).json(data);
}
