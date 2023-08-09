import { SERVER_URL } from '#/endpoints/Fetcher';
import { USER_SERVER_ENDPOINTS } from '#/endpoints/User';
import Link from 'next/link';
import { useRouter } from 'next/router';
import { useContainerContext } from '../Container';

export default function AdminSidebar() {
	const { heartbeatInfo } = useContainerContext();
	const { heartbeat } = heartbeatInfo;
	const router = useRouter();
	return (
		<>
			<h3 className="text-2xl">Welcome, {heartbeat?.username}</h3>
			<Link className="btn btn-accent w-1/2" href="/" passHref>
				Home
			</Link>
			<Link className="btn btn-accent w-1/2" href="/list/wish" passHref>
				Wish List
			</Link>
			<Link className="btn btn-accent w-1/2" href="/list/catch" passHref>
				Catch List
			</Link>
			<Link className="btn btn-accent w-1/2" href="/pokemon/add" passHref>
				Add Pokemon
			</Link>
			<Link className="btn btn-accent w-1/2" href="/users" passHref>
				Users
			</Link>
			<Link className="btn btn-accent w-1/2" href="/users/add" passHref>
				Add User
			</Link>
			<button
				onClick={async () => {
					await fetch(`${SERVER_URL}${USER_SERVER_ENDPOINTS.LOGOUT}`);
					router.reload();
				}}
				className="btn btn-accent w-1/2"
			>
				Logout
			</button>
		</>
	);
}
