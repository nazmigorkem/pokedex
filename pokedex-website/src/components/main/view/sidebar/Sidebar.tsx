import { useEffect } from 'react';
import { themeChange } from 'theme-change';
import { useContainerContext } from '../Container';
import AdminSidebar from './AdminSidebar';
import AnonymousSideBar from './AnonymousSideBar';
import TrainerSideBar from './TrainerSideBar';

export default function Sidebar() {
	const { heartbeatInfo } = useContainerContext();
	const { heartbeat } = heartbeatInfo;
	useEffect(() => {
		themeChange(false);
	}, []);
	return (
		<div className="min-h-screen flex flex-col items-center justify-center bg-base-300 w-1/6 fixed gap-5">
			{heartbeat.roles.includes('ROLE_ANONYMOUS') ? (
				<AnonymousSideBar />
			) : heartbeat.roles.includes('ROLE_ADMIN') ? (
				<AdminSidebar />
			) : (
				<TrainerSideBar />
			)}
			<select data-choose-theme className="w-1/2 select select-bordered">
				<option value="dark">Dark</option>
				<option value="light">Light</option>
				<option value="dracula">Dracula</option>
				<option value="night">Night</option>
				<option value="luxury">Luxury</option>
				<option value="synthwave">Synthwave</option>
			</select>
		</div>
	);
}
