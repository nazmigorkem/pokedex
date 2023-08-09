import Pokecard from '#/components/Pokemon/Pokecard';
import { useEffect } from 'react';
import InfiniteScroll from 'react-infinite-scroll-component';

export default function InfiniteScrollPokelist({
	items,
	hasMore,
	fetchFunction,
	searchValue,
}: {
	items: PokemonResponse[];
	hasMore: boolean;
	fetchFunction: () => void;
	searchValue?: string;
}) {
	useEffect(fetchFunction, []);

	return (
		<div className="m-20 flex flex-col gap-5 min-h-[90vh] items-center">
			<InfiniteScroll
				key={searchValue}
				dataLength={items.length}
				next={fetchFunction}
				hasMore={hasMore}
				loader={<div className="loading loading-lg flex justify-center mt-5"></div>}
				endMessage={<></>}
			>
				<div className="grid grid-cols-5 gap-5 p-10">
					{items.map((data, index) => (
						<Pokecard
							variants={{
								hidden: () => ({
									opacity: 0,
									x: -30,
								}),
								visible: (key) => ({
									opacity: 1,
									x: -0,
									transition: {
										delay: key * 0.1,
										bounce: false,
									},
								}),
							}}
							whileHover={{
								scale: 1.05,
								transition: {
									duration: 0.1,
								},
							}}
							whileTap={{
								scale: 0.95,
								transition: {
									duration: 0.1,
								},
							}}
							animate="visible"
							initial="hidden"
							key={index}
							custom={index % 10}
							pokemon={data}
						/>
					))}
				</div>
			</InfiniteScroll>
		</div>
	);
}
