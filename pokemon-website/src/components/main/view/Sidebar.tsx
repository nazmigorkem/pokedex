import { BASE_SERVER_URL, HEARTBEAT_ENDPOINT, LOGOUT_ENDPOINT, useHeartBeat } from '#/endpoints/User';
import { useEffect } from 'react';
import Login from '../session/Login';
import Signup from '../session/Signup';
import { useSWRConfig } from 'swr';

export default function Sidebar() {
	const { data, error, isLoading } = useHeartBeat();
	const { mutate } = useSWRConfig();

	useEffect(() => {
		mutate(HEARTBEAT_ENDPOINT, false);
	}, []);

	return (
		<div className="min-h-screen flex flex-col items-center justify-center bg-base-300 w-1/6 fixed gap-5">
			{isLoading ? (
				<div className="loading loading-spinner loading-lg"></div>
			) : data?.username === undefined ? (
				<>
					<Login />
					<Signup />
				</>
			) : (
				<button
					onClick={async () => {
						await fetch(`${BASE_SERVER_URL}${LOGOUT_ENDPOINT}`);
						await mutate(HEARTBEAT_ENDPOINT, false);
					}}
					className="btn btn-accent w-1/2"
				>
					Logout
				</button>
			)}
		</div>
	);
}
