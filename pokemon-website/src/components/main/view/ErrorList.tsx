import React from 'react';

export default function ErrorList({ errors }: { errors: string[] }) {
	return (
		<div className="alert alert-error rounded-lg">
			<ul>
				{errors.map((error, index) => (
					<li key={index}>{error}</li>
				))}
			</ul>
		</div>
	);
}
