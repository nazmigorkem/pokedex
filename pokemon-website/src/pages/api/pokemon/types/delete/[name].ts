import { BACKEND_URL } from '#/endpoints/Fetcher';
import { POKEMON_BACKEND_ENDPOINTS } from '#/endpoints/Pokemon';
import { sessionOptions } from '#/session/options';
import cookie from 'cookie';
import { withIronSessionApiRoute } from 'iron-session/next';
import { NextApiRequest, NextApiResponse } from 'next';

export default withIronSessionApiRoute(handle, sessionOptions);

async function handle(req: NextApiRequest, res: NextApiResponse) {
	if (!req.session.user?.JSESSIONID) {
		return res.status(401).send({ username: undefined });
	}

	const { name } = req.query;

	const response = await fetch(`${BACKEND_URL}${POKEMON_BACKEND_ENDPOINTS.TYPES.DELETE}/${name}`, {
		headers: {
			Cookie: cookie.serialize('JSESSIONID', req.session.user.JSESSIONID),
		},
		method: 'DELETE',
	});

	if (!response.ok) {
		return res.status(response.status).send(response.status === 400 ? await response.json() : { errors: ['Failed to fetch.'] });
	}

	res.status(200).end();
}
