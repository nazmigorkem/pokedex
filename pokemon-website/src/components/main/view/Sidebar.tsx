import { SERVER_URL, SERVER_HEARTBEAT_ENDPOINT, SERVER_LOGOUT_ENDPOINT, useHeartBeat } from '#/endpoints/User';
import { useEffect } from 'react';
import Login from '../session/Login';
import Signup from '../session/Signup';
import { useSWRConfig } from 'swr';

export default function Sidebar() {
	const { data, error, isLoading } = useHeartBeat();
	const { mutate } = useSWRConfig();

	useEffect(() => {
		mutate(SERVER_HEARTBEAT_ENDPOINT, undefined);
	}, []);

	return (
		<div className="min-h-screen flex flex-col items-center justify-center bg-base-300 w-1/6 fixed gap-5">
			{isLoading || data === undefined ? (
				<div className="loading loading-spinner loading-lg"></div>
			) : data?.username === undefined ? (
				<>
					<Login />
					<Signup />
				</>
			) : (
				<button
					onClick={async () => {
						await fetch(`${SERVER_URL}${SERVER_LOGOUT_ENDPOINT}`);
						await mutate(SERVER_HEARTBEAT_ENDPOINT, false);
					}}
					className="btn btn-accent w-1/2"
				>
					Logout
				</button>
			)}
		</div>
	);
}
