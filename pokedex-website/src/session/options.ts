import { IronSessionOptions } from 'iron-session';

export const sessionOptions: IronSessionOptions = {
	cookieName: 'session',
	password: 'complex_password_at_least_32_characters_long',
	cookieOptions: {
		secure: false,
		maxAge: 60 * 60 * 24 * 7,
		path: '/',
		sameSite: 'strict',
	},
};

declare module 'iron-session' {
	interface IronSessionData {
		user?: {
			username: string;
			JSESSIONID: string;
		};
	}
}
