import { SERVER_URL } from '#/endpoints/Fetcher';
import { USER_SERVER_ENDPOINTS } from '#/endpoints/User';
import { useRouter } from 'next/router';

export default function Logout() {
	const router = useRouter();
	return (
		<button
			onClick={async () => {
				await fetch(`${SERVER_URL}${USER_SERVER_ENDPOINTS.LOGOUT}`);
				router.push('/').then(() => router.reload());
			}}
			className="btn btn-accent w-1/2"
		>
			Logout
		</button>
	);
}
