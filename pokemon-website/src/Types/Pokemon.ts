type PokemonResponse = {
	name: string;
	imageUrl: string;
	types: [PokemonTypeResponse] | [PokemonTypeResponse, PokemonTypeResponse];
	description: string;
	health: number;
	attack: number;
	defense: number;
	specialAttack: number;
	specialDefense: number;
	speed: number;
};

type PokemonTypeResponse = {
	name: string;
	color: string;
};

type PokemonAddRequest = {
	name: string;
	imageUrl: string;
	types: [string] | [string, string];
	description: string;
	health: number;
	attack: number;
	defense: number;
	specialAttack: number;
	specialDefense: number;
	speed: number;
};
