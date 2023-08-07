import { SERVER_URL } from '#/endpoints/Fetcher';
import { SERVER_LOGOUT_ENDPOINT } from '#/endpoints/User';
import { useRouter } from 'next/router';
import { useContainerContext } from '../Container';
import Link from 'next/link';

export default function TrainerSideBar() {
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
			<button
				onClick={async () => {
					await fetch(`${SERVER_URL}${SERVER_LOGOUT_ENDPOINT}`);
					router.reload();
				}}
				className="btn btn-accent w-1/2"
			>
				Logout
			</button>
		</>
	);
}
