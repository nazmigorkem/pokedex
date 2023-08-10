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
		<div className="flex flex-col items-center mt-20 w-[25vw] mx-auto">
			<div className="flex gap-3 w-full">
				<input
					onChange={(e) => {
						setSearchValue(e.target.value);
					}}
					type="text"
					className="input input-accent input-bordered w-full"
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
			<div className="m-20 flex flex-col gap-5 w-full">
				{pokemonTypes?.map((x, index) => (
					<PokemonTypeCard
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
						custom={index}
						data={x}
					/>
				))}
			</div>
		</div>
	);
}

Users.Auth = Admin;
