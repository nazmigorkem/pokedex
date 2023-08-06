import { NextApiRequest, NextApiResponse } from 'next';

export default async (req: NextApiRequest, res: NextApiResponse) => {
	const { username, password } = req.body;

	const signUpResponse = await fetch('http://localhost:8080/api/user/add', {
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

	const loginResponse = await fetch('http://localhost:8080/api/user/login', {
		method: 'POST',
		headers: {
			'Content-Type': 'x-www-form-urlencoded',
		},
		body: new URLSearchParams({
			username,
			password,
		}),
	});

	const JSSESSIONID = loginResponse.headers.get('set-cookie');

	if (!JSSESSIONID) {
		return res.status(500).json({ errors: ['Failed to create user'] });
	}

	res.setHeader('Set-Cookie', JSSESSIONID);
	res.status(200).json({ message: 'User created' });
};
