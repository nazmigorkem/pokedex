import Pokecard from '#/components/Pokecard';

export default function Home() {
	return (
		<div className="w-5/6 float-right min-h-screen">
			<div className="m-20 flex flex-col gap-5">
				<h1 className="text-5xl font-bold">Pokecards</h1>
				<div className="grid grid-cols-5 gap-5">
					<Pokecard
						imageURL="https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png"
						description="Lorem ipsum dolor sit amet consectetur adipisicing elit."
						types={['fire', 'fire']}
					/>
					<Pokecard
						imageURL="https://assets.pokemon.com/assets/cms2/img/pokedex/full/327.png"
						description="Lorem ipsum dolor sit amet consectetur adipisicing elit. Iure, assumenda. Similique dicta neque dolorum quisquam explicabo
				praesentium? Possimus iusto magni voluptas sed? Alias esse quisquam dolores fugit ratione illum sapiente?"
						types={['fire']}
					/>
					<Pokecard
						imageURL="https://assets.pokemon.com/assets/cms2/img/pokedex/full/006.png"
						description="Lorem ipsum dolor sit amet consectetur adipisicing elit. Iure, assumenda. Similique dicta neque dolorum quisquam explicabo
				praesentium? Possimus iusto magni voluptas sed? Alias esse quisquam dolores fugit ratione illum sapiente?"
						types={['fire', 'fire']}
					/>
					<Pokecard
						imageURL="https://assets.pokemon.com/assets/cms2/img/pokedex/full/034.png"
						description="Lorem ipsum dolor sit amet consectetur adipisicing elit. Iure, assumenda. Similique dicta neque dolorum quisquam explicabo
				praesentium? Possimus iusto magni voluptas sed? Alias esse quisquam dolores fugit ratione illum sapiente?"
						types={['fire', 'fire']}
					/>
					<Pokecard
						imageURL="https://assets.pokemon.com/assets/cms2/img/pokedex/full/541.png"
						description="Lorem ipsum dolor sit amet consectetur adipisicing elit. Iure, assumenda. Similique dicta neque dolorum quisquam explicabo
				praesentium? Possimus iusto magni voluptas sed? Alias esse quisquam dolores fugit ratione illum sapiente?"
						types={['fire', 'fire']}
					/>
					<Pokecard
						imageURL="https://assets.pokemon.com/assets/cms2/img/pokedex/full/331.png"
						description="Lorem ipsum dolor sit amet consectetur adipisicing elit. Iure, assumenda. Similique dicta neque dolorum quisquam explicabo
				praesentium? Possimus iusto magni voluptas sed? Alias esse quisquam dolores fugit ratione illum sapiente?"
						types={['fire', 'fire']}
					/>
					<Pokecard
						imageURL="https://assets.pokemon.com/assets/cms2/img/pokedex/full/313.png"
						description="Lorem ipsum dolor sit amet consectetur adipisicing elit. Iure, assumenda. Similique dicta neque dolorum quisquam explicabo
				praesentium? Possimus iusto magni voluptas sed? Alias esse quisquam dolores fugit ratione illum sapiente?"
						types={['fire', 'fire']}
					/>
				</div>
			</div>
		</div>
	);
}
