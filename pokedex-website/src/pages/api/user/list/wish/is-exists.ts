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
	const { username, pokemonName } = req.query;

	const query = new URLSearchParams({
		username: req.session.user.username,
		pokemonName: pokemonName as string,
	});

	const response = await fetch(`${BACKEND_URL}${USER_BACKEND_ENDPOINTS.WISH_LIST.IS_EXIST}?${query}`, {
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
