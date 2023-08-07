import { BACKEND_URL } from '#/endpoints/Fetcher';
import { BACKEND_POKEMON_ENDPOINT, BACKEND_POKEMON_GET_ENDPOINT } from '#/endpoints/Pokemon';
import { BACKEND_GET_USER_CATCH_LIST_ENDPOINT } from '#/endpoints/User';
import { sessionOptions } from '#/session/options';
import cookie from 'cookie';
import { withIronSessionApiRoute } from 'iron-session/next';
import { NextApiRequest, NextApiResponse } from 'next';

export default withIronSessionApiRoute(handle, sessionOptions);

async function handle(req: NextApiRequest, res: NextApiResponse) {
	if (!req.session.user?.JSESSIONID) {
		return res.status(401).send({ username: undefined });
	}
	const { pageNumber, pageSize } = req.query;

	const query = new URLSearchParams({
		pageNumber: pageNumber as string,
		pageSize: pageSize as string,
	});

	const response = await fetch(`${BACKEND_URL}${BACKEND_GET_USER_CATCH_LIST_ENDPOINT}/${req.session.user.username}?${query}`, {
		headers: {
			Cookie: cookie.serialize('JSESSIONID', req.session.user.JSESSIONID),
		},
	});

	if (!response.ok) {
		return res.status(response.status).send({ errors: ['Failed to fetch.'] });
	}

	const data = await response.json();

	res.status(200).json(data);
}
