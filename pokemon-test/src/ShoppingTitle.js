import React from 'react';

export default function ShoppingTitle({ title, numItems }) {
	return (
		<>
			<h1>{title}</h1>
			<h2>{numItems} items</h2>
		</>
	);
}
