import Pokecard from '#/components/Pokemon/Pokecard';
import { useState } from 'react';
import InfiniteScroll from 'react-infinite-scroll-component';

export default function Home() {
	const [items, setItems] = useState([...Array(25)]);
	return (
		<div className="w-5/6 float-right min-h-screen">
			<div className="m-20 flex flex-col gap-5 min-h-[90%]">
				<h1 className="text-5xl font-bold">Pokecards</h1>
				<InfiniteScroll
					dataLength={items.length}
					next={async () => {
						await new Promise((resolve) => setTimeout(resolve, 1000));
						setItems(items.concat([...Array(25)]));
					}}
					hasMore={true}
					loader={<h4 className="self-center">Loading...</h4>}
					endMessage={
						<p style={{ textAlign: 'center' }}>
							<b>Yay! You have seen it all</b>
						</p>
					}
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
		</div>
	);
}
