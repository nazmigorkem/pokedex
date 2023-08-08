import { useContainerContext } from '../Container';
import AdminSidebar from './AdminSidebar';
import AnonymousSideBar from './AnonymousSideBar';
import TrainerSideBar from './TrainerSideBar';

export default function Sidebar() {
	const { heartbeatInfo } = useContainerContext();
	const { isLoading, heartbeat } = heartbeatInfo;

	return (
		<div className="min-h-screen flex flex-col items-center justify-center bg-base-300 w-1/6 fixed gap-5">
			{isLoading || heartbeat === undefined ? (
				<div className="loading loading-spinner loading-lg"></div>
			) : heartbeat?.username === undefined ? (
				<AnonymousSideBar />
			) : heartbeat.roles.includes('ROLE_ADMIN') ? (
				<AdminSidebar />
			) : (
				<TrainerSideBar />
			)}
		</div>
	);
}
