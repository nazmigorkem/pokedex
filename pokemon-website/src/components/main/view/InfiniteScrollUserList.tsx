import { UserSearchResponse } from '#/Types/User';
import UserCard from '#/components/user/UserCard';
import { useEffect } from 'react';
import InfiniteScroll from 'react-infinite-scroll-component';

export default function InfiniteScrollUserList({
	items,
	hasMore,
	fetchFunction,
}: {
	items: UserSearchResponse[];
	hasMore: boolean;
	fetchFunction: () => void;
}) {
	useEffect(fetchFunction, []);
	console.log(items.length);

	return (
		<div className="m-20 flex flex-col gap-5 min-h-[90vh] w-full">
			<InfiniteScroll
				dataLength={items.length}
				next={fetchFunction}
				hasMore={hasMore}
				loader={<div className="loading loading-lg flex justify-center mt-5"></div>}
				endMessage={
					items.length !== 0 ? (
						<div className="text-xl text-center mt-10">End of the list.</div>
					) : (
						<div className="text-xl text-center">No users found in this list.</div>
					)
				}
			>
				<div className="flex flex-col gap-5">
					{items.map((data, index) => (
						<UserCard
							variants={{
								hidden: () => ({
									opacity: 0,
									y: -30,
								}),
								visible: (key) => ({
									opacity: 1,
									y: -0,
									transition: {
										delay: key * 0.1,
										bounce: false,
									},
								}),
							}}
							animate="visible"
							initial="hidden"
							key={index}
							custom={index % 10}
							data={data}
						/>
					))}
				</div>
			</InfiniteScroll>
		</div>
	);
}
