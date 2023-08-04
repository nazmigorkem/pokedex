import React from 'react';

export default function ShoppingList({ items, title }) {
	return (
		<>
			<h2>{title}</h2>
			<ol className="item-list">
				{items.map((item, i) => (
					<li key={i}>
						<h4>{item}</h4>
					</li>
				))}
			</ol>
		</>
	);
}
