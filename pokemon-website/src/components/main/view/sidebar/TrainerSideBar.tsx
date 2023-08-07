import { SERVER_URL } from '#/endpoints/Fetcher';
import { SERVER_LOGOUT_ENDPOINT } from '#/endpoints/User';
import { useRouter } from 'next/router';
import { useContainerContext } from '../Container';

export default function TrainerSideBar() {
	const { heartbeatInfo } = useContainerContext();
	const { heartbeat } = heartbeatInfo;
	const router = useRouter();
	return (
		<>
			<h3 className="text-2xl">Welcome, {heartbeat?.username}</h3>
			<a href="/wish-list" className="btn btn-accent w-1/2">
				Wish List
			</a>
			<a href="/catch-list" className="btn btn-accent w-1/2">
				Catch List
			</a>
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
