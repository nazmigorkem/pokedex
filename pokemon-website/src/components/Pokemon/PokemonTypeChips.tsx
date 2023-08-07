export default function PokemonTypeChips({ typeName, typeColor }: { typeName: string; typeColor: string }) {
	return (
		<div
			style={{ backgroundColor: typeColor }}
			className={`text-black py-1 px-3 rounded-md text-sm font-semibold text-opacity-60 text-center truncate`}
		>
			{typeName}
		</div>
	);
}
