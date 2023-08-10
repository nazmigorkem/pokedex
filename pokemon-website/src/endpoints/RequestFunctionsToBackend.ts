import { NextApiRequest, NextApiResponse } from 'next';

import { ROLES } from '#/Types/User';
import cookie from 'cookie';
import { BACKEND_URL } from './Fetcher';

export const REQUEST_WITH_JSON_BODY = (endPoint: string, method: 'POST' | 'PUT') => {
	return async (req: NextApiRequest, res: NextApiResponse) => {
		if (!req.session.user?.JSESSIONID) {
			return res.status(401).send({ username: '', roles: [ROLES.anonymous] });
		}

		const response = await fetch(`${BACKEND_URL}${endPoint}`, {
			headers: {
				Cookie: cookie.serialize('JSESSIONID', req.session.user.JSESSIONID),
				'Content-Type': 'application/json',
			},
			method,
			body: JSON.stringify(req.body),
		});

		if (!response.ok) {
			return res.status(response.status).send(response.status === 400 ? await response.json() : { errors: ['Failed to fetch.'] });
		}

		const data = await response.json();

		res.status(200).json(data);
	};
};

export const DELETE_METHOD_WITH_PATH_QUERY_NAME = (endPoint: string) => {
	return async (req: NextApiRequest, res: NextApiResponse) => {
		if (!req.session.user?.JSESSIONID) {
			return res.status(401).send({ username: '', roles: [ROLES.anonymous] });
		}

		const { name } = req.query;

		const response = await fetch(`${BACKEND_URL}${endPoint}/${name}`, {
			headers: {
				Cookie: cookie.serialize('JSESSIONID', req.session.user.JSESSIONID),
			},
			method: 'DELETE',
		});

		if (!response.ok) {
			return res.status(response.status).send(response.status === 400 ? await response.json() : { errors: ['Failed to fetch.'] });
		}

		res.status(200).end();
	};
};

export const GET_METHOD_WITH_PAGE_QUERIES = (endPoint: string) => {
	return async (req: NextApiRequest, res: NextApiResponse) => {
		if (!req.session.user?.JSESSIONID) {
			return res.status(401).send({ username: '', roles: [ROLES.anonymous] });
		}

		const { pageNumber, pageSize, name } = req.query;

		const query = new URLSearchParams({
			pageNumber: pageNumber as string,
			pageSize: pageSize as string,
			name: name as string,
		});

		const response = await fetch(`${BACKEND_URL}${endPoint}?${query}`, {
			headers: {
				Cookie: cookie.serialize('JSESSIONID', req.session.user.JSESSIONID),
			},
		});

		if (!response.ok) {
			return res.status(response.status).send(response.status === 400 ? await response.json() : { errors: ['Failed to fetch.'] });
		}

		const data = await response.json();

		res.status(200).json(data);
	};
};

export const GET_METHOD_WITH_SEARCH_SLUG = (endPoint: string) => {
	return async (req: NextApiRequest, res: NextApiResponse) => {
		if (!req.session.user?.JSESSIONID) {
			return res.status(401).send({ username: '', roles: [ROLES.anonymous] });
		}

		const { name } = req.query;

		const response = await fetch(`${BACKEND_URL}${endPoint}/${name}`, {
			headers: {
				Cookie: cookie.serialize('JSESSIONID', req.session.user.JSESSIONID),
			},
		});

		if (!response.ok) {
			return res.status(response.status).send(response.status === 400 ? await response.json() : { errors: ['Failed to fetch.'] });
		}

		const data = await response.json();

		res.status(200).json(data);
	};
};
