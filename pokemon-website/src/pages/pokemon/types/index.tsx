import PokemonTypeCard from '#/components/Pokemon/PokemonTypeCard';
import Admin from '#/components/main/session/auth/Admin';
import { usePokemonTypes } from '#/endpoints/Pokemon';
import { useRouter } from 'next/router';
import { useState } from 'react';

export default function Users() {
	const router = useRouter();

	const [searchValue, setSearchValue] = useState('');
	const { data: pokemonTypes } = usePokemonTypes();

	return (
		<div className="flex flex-col items-center mt-20">
			<div className="flex gap-3">
				<input
					onChange={(e) => {
						setSearchValue(e.target.value);
					}}
					type="text"
					className="input input-accent input-bordered"
					placeholder="Search for a pokemon type"
				/>
				<button
					onClick={() => {
						router.push(`/pokemon/types/search/${searchValue}`);
					}}
					className="btn btn-outline btn-accent"
				>
					<i className="fas fa-search"></i>
				</button>
			</div>
			<div className="min-w-[30vw] m-20 flex flex-col gap-5">
				{pokemonTypes?.map((x) => (
					<PokemonTypeCard data={x} />
				))}
			</div>
		</div>
	);
}

Users.Auth = Admin;
