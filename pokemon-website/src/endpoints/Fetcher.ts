export const fetcher = async (input: RequestInfo, init?: RequestInit) => {
	const result = await fetch(input, {
		...init,
		headers: {
			...init?.headers,
		},
	});

	return init?.method !== 'DEL' ? await result.json() : result.status;
};
