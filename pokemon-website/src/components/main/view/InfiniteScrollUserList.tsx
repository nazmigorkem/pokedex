import InfiniteScroll from 'react-infinite-scroll-component';
import { useEffect } from 'react';
import { UserSearchResponse } from '#/Types/UserResponse';
import UserCard from '#/components/user/UserCard';

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

	return (
		<div className="m-20 flex flex-col gap-5 min-h-[90vh] items-center">
			<InfiniteScroll
				dataLength={items.length}
				next={fetchFunction}
				hasMore={hasMore}
				loader={<div className="loading loading-lg flex justify-center mt-5"></div>}
				endMessage={<></>}
			>
				<div className="flex flex-col min-w-[30vw] gap-5">
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
