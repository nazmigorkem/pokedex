import { sessionOptions } from '#/session/options';
import { withIronSessionApiRoute } from 'iron-session/next';
import { NextApiRequest, NextApiResponse } from 'next';
import cookie from 'cookie';
import { BACKEND_URL, BACKEND_USER_GET_ENDPOINT, BACKEND_USER_LOGIN_ENDPOINT } from '#/endpoints/User';
export default withIronSessionApiRoute(handle, sessionOptions);

async function handle(req: NextApiRequest, res: NextApiResponse) {
	const { username, password } = req.body as { username: string; password: string };

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

	if (loginResponse.status === 403) {
		return res.status(loginResponse.status).json({ errors: ['Invalid username or password.'] });
	}

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
		JSESSIONID: JSESSIONID,
	};

	await req.session.save();

	res.send({ message: 'Logged in.' });
}
