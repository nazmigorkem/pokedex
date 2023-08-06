import { useState } from 'react';
import Signup from './Signup';

export default function Sidebar() {
	return (
		<div className="min-h-screen flex flex-col items-center justify-center bg-base-300 w-1/6 fixed gap-5">
			<div className="btn btn-accent w-1/2">Login</div>
			<Signup />
		</div>
	);
}
