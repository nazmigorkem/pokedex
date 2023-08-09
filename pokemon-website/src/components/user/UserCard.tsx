import { UserSearchResponse } from '#/Types/User';
import { motion } from 'framer-motion';
import { useRouter } from 'next/router';
import React from 'react';

const UserCard = React.forwardRef(({ data }: { data: UserSearchResponse }, ref: any) => {
	const router = useRouter();
	return (
		<button
			onClick={() => {
				router.push(`/users/${data.username}`);
			}}
			ref={ref}
			className="mx-auto w-full p-5 rounded-md font-bold flex items-center justify-between text-base-content cursor-pointer bg-base-300 hover:bg-base-200 transition-colors duration-200"
		>
			{data.username}
			{data.roles.map((role, index) => {
				return (
					<div key={index} className="flex gap-5 text-xs">
						<span className="bg-primary text-primary-content rounded-md p-3 select-none">{role}</span>
					</div>
				);
			})}
		</button>
	);
});

export default motion(UserCard, { forwardMotionProps: true });
