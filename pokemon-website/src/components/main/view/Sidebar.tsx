import { SERVER_LOGOUT_ENDPOINT } from '#/endpoints/User';
import Login from '../session/Login';
import Signup from '../session/Signup';
import { SERVER_URL } from '#/endpoints/Fetcher';
import { useContainerContext } from './Container';
import { useRouter } from 'next/router';

export default function Sidebar() {
	const { heartbeatInfo } = useContainerContext();
	const { isLoading, heartbeat } = heartbeatInfo;
	const router = useRouter();

	return (
		<div className="min-h-screen flex flex-col items-center justify-center bg-base-300 w-1/6 fixed gap-5">
			{isLoading || heartbeat === undefined ? (
				<div className="loading loading-spinner loading-lg"></div>
			) : heartbeat?.username === undefined ? (
				<>
					<Login />
					<Signup />
				</>
			) : (
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
			)}
		</div>
	);
}
