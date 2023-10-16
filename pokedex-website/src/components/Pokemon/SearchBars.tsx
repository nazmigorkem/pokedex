import { useRouter } from 'next/router';
import { Dispatch, SetStateAction } from 'react';
export default function SearchBars({ searchValue, setSearchValue }: { searchValue: string; setSearchValue: Dispatch<SetStateAction<string>> }) {
	const router = useRouter();

	return (
		<div className="flex flex-col gap-5">
			<div className="flex gap-3 w-[25vw] mx-auto">
				<input
					onChange={(e) => {
						setSearchValue(e.target.value);
					}}
					type="text"
					className="input input-accent input-bordered w-full"
					placeholder="Search for a pokemon"
				/>
				<button
					onClick={() => {
						router.push(`/pokemon/search/${searchValue}`).then(router.pathname.startsWith('/pokemon/search') ? () => {} : () => {});
					}}
					className="btn btn-outline btn-accent"
				>
					<i className="fas fa-search"></i>
				</button>
			</div>
			<div className="flex gap-3 w-[25vw] mx-auto">
				<input
					onChange={(e) => {
						setSearchValue(e.target.value);
					}}
					type="text"
					className="input input-accent input-bordered w-full"
					placeholder="Search for a pokemon by its type"
				/>
				<button
					onClick={() => {
						router
							.push(`/pokemon/search-by-type/${searchValue}`)
							.then(router.pathname.startsWith('/pokemon/search-by-type') ? router.reload : () => {});
					}}
					className="btn btn-outline btn-accent"
				>
					<i className="fas fa-search"></i>
				</button>
			</div>
		</div>
	);
}
