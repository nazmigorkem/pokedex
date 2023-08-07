import Pokecard from '#/components/Pokemon/Pokecard';
import InfiniteScroll from 'react-infinite-scroll-component';
import { useEffect } from 'react';

export default function InfiniteScrollPokelist({ items, hasMore, fetchFunction }: { items: any[]; hasMore: boolean; fetchFunction: () => void }) {
	useEffect(fetchFunction, []);

	return (
		<div className="m-20 flex flex-col gap-5 min-h-[90vh]">
			<h1 className="text-5xl font-bold">Pokecards</h1>
			<InfiniteScroll
				dataLength={items.length}
				next={fetchFunction}
				hasMore={hasMore}
				loader={<div className="mx-auto loading loading-lg flex justify-center mt-5"></div>}
				endMessage={<></>}
			>
				<div className="grid grid-cols-5 gap-5">
					{items.map((_, index) => (
						<Pokecard
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
							custom={index % 25}
							imageURL={'https://assets.pokemon.com/assets/cms2/img/pokedex/full/' + String(index + 1).padStart(3, '0') + '.png'}
							description="Lorem ipsum dolor sit amet consectetur adipisicing elit."
							types={['fire', 'fire']}
						/>
					))}
				</div>
			</InfiniteScroll>
		</div>
	);
}
