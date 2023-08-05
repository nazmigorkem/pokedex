export default function PokemonTypeChips({ typeName }: { typeName: string }) {
	return <div className="bg-yellow-400 text-black py-1 px-3 rounded-md text-sm font-semibold text-opacity-60 text-center truncate">{typeName}</div>;
}
