import { Dispatch, SetStateAction } from 'react';

export const fetcher = (url: string) => fetch(url).then((res) => res.json());
export const BACKEND_URL = 'http://localhost:8080/api';
export const SERVER_URL = 'http://localhost:3000';

export const fetchForInfiniteScroll = (
	url: string,
	pageNumber: number,
	items: any[],
	searchValue: string,
	setHasMore: Dispatch<SetStateAction<boolean>>,
	setPageNumber: Dispatch<SetStateAction<number>>,
	setItems: Dispatch<SetStateAction<any[]>>
) => {
	return () => {
		const query = new URLSearchParams({
			pageNumber: pageNumber.toString(),
			pageSize: '10',
			name: searchValue,
		});
		fetch(`${SERVER_URL}${url}?${query}`).then((response) => {
			if (response.status !== 200) {
				setHasMore(false);
				return;
			}
			response.json().then((data) => {
				setPageNumber(pageNumber + 1);
				setHasMore(!data.last);
				setItems(items.concat(data.content));
			});
		});
	};
};
