import { NextApiRequest, NextApiResponse } from 'next';

import cookie from 'cookie';
import { BACKEND_URL } from './Fetcher';

export const POST_METHOD = (endPoint: string) => {
	return async (req: NextApiRequest, res: NextApiResponse) => {
		if (!req.session.user?.JSESSIONID) {
			return res.status(401).send({ username: undefined });
		}

		const response = await fetch(`${BACKEND_URL}${endPoint}`, {
			headers: {
				Cookie: cookie.serialize('JSESSIONID', req.session.user.JSESSIONID),
				'Content-Type': 'application/json',
			},
			method: 'POST',
			body: JSON.stringify(req.body),
		});

		if (!response.ok) {
			return res.status(response.status).send(response.status === 400 ? await response.json() : { errors: ['Failed to fetch.'] });
		}

		const data = await response.json();

		res.status(200).json(data);
	};
};
