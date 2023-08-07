type PokemonResponse = {
	name: string;
	imageUrl: string;
	types: PokemonType[];
	description: string;
	health: number;
	attack: number;
	defense: number;
	specialAttack: number;
	specialDefense: number;
	speed: number;
};

type PokemonType = {
	name: string;
	color: string;
};
