import { NextApiRequest, NextApiResponse } from 'next';
import { withIronSessionApiRoute } from 'iron-session/next';
import { sessionOptions } from '#/session/options';
import cookie from 'cookie';
import { BACKEND_USER_ADD_ENDPOINT, BACKEND_USER_GET_ENDPOINT, BACKEND_USER_LOGIN_ENDPOINT } from '#/endpoints/User';
import { BACKEND_URL } from '#/endpoints/Fetcher';

export default withIronSessionApiRoute(handle, sessionOptions);

async function handle(req: NextApiRequest, res: NextApiResponse) {
	const { username, password } = req.body as { username: string; password: string };

	if (!username || !password) {
		return res.status(400).json({ errors: ['Missing username or password'] });
	}

	const signUpResponse = await fetch(`${BACKEND_URL}${BACKEND_USER_ADD_ENDPOINT}`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({ username, password }),
	});

	if (!signUpResponse.ok) {
		const errors = await signUpResponse.json();
		return res.status(signUpResponse.status).json(errors);
	}

	const loginResponse = await fetch(`${BACKEND_URL}${BACKEND_USER_LOGIN_ENDPOINT}`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded',
		},
		body: new URLSearchParams({
			username,
			password,
		}),
	});

	const JSESSIONID = cookie.parse(loginResponse.headers.get('set-cookie') ?? '').JSESSIONID;

	if (!JSESSIONID) {
		return res.status(500).json({ errors: ['Failed to create user'] });
	}

	const userResponse = await fetch(`${BACKEND_URL}${BACKEND_USER_GET_ENDPOINT}/${username}`, {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
			Cookie: cookie.serialize('JSESSIONID', JSESSIONID),
		},
	});

	req.session.user = {
		username,
		JSESSIONID,
	};

	await req.session.save();

	res.send({ message: 'User created' });
}
