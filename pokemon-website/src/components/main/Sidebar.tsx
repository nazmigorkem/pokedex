import { useContainerContext } from './Context';
import Login from './Login';
import Signup from './Signup';

export default function Sidebar() {
	const { heartbeat } = useContainerContext();

	return (
		<div className="min-h-screen flex flex-col items-center justify-center bg-base-300 w-1/6 fixed gap-5">
			{heartbeat === undefined ? (
				<>
					<Login />
					<Signup />
				</>
			) : (
				<div className="btn btn-accent w-1/2">Logout</div>
			)}
		</div>
	);
}
