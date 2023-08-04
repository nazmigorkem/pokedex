import './App.css';
import ShoppingList from './ShoppingList';
import ShoppingTitle from './ShoppingTitle';

function App() {
	return (
		<div>
			<ShoppingTitle title="My Shopping List" numItems={9} />
			<ShoppingList items={['Apple', 'Bread', 'Cheese']} title="Food" />
			<ShoppingList items={['Shirt', 'Pants', 'Hat']} title="Clothes" />
			<ShoppingList items={['Pen', 'Paper', 'Glue']} title="Supplies" />
		</div>
	);
}

export default App;
