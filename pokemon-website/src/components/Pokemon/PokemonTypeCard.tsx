import { motion } from 'framer-motion';
import { useRouter } from 'next/router';
import React from 'react';

const PokemonTypeCard = React.forwardRef(({ data }: { data: PokemonTypeResponse }, ref: any) => {
	const router = useRouter();
	return (
		<button
			onClick={() => {
				router.push(`/pokemon/types/${data.name}/edit`);
			}}
			ref={ref}
			className="mx-auto w-full p-5 rounded-md font-bold flex items-center justify-between text-base-content cursor-pointer bg-base-300 hover:bg-base-200 transition-colors duration-200"
		>
			{data.name}
		</button>
	);
});

export default motion(PokemonTypeCard, { forwardMotionProps: true });
