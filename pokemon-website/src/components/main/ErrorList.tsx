import React from 'react';

export default function ErrorList({ errors }: { errors: string[] }) {
	return (
		<ul>
			{errors.map((error, index) => (
				<li key={index}>{error}</li>
			))}
		</ul>
	);
}
