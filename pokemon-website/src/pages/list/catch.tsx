import { SERVER_URL } from '#/endpoints/Fetcher';
import { useState } from 'react';

export default function Catch() {
	const [hasMore, setHasMore] = useState(true);
	const [pageNumber, setPageNumber] = useState(0);
	const [items, setItems] = useState([] as any[]);

	return <div>catch</div>;
}
