type PokemonResponse = Record<string, string | number | [PokemonTypeResponse] | [PokemonTypeResponse, PokemonTypeResponse]> & {
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

type PokemonTypeAddRequest = {
	name: string;
	color: string;
};

type PokemonAddRequest = Record<string, string | number | [string] | [string, string]> & {
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

type PokemonEditRequest = Record<string, string | number | [string] | [string, string]> & {
	searchName: string;
	name?: string;
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
