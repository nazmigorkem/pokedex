import React from 'react';

export default function StatChip({ stat, statName }: { stat: number; statName: string }) {
	return (
		<div className="bg-base-300 text-base-content rounded-md p-3 flex flex-col items-center justify-center">
			<span className="font-bold text-accent">{statName}</span>
			<span className="font-bold">{stat}</span>
		</div>
	);
}
