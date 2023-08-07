export const fetcher = (url: string) => fetch(url).then((res) => res.json());
export const BACKEND_URL = 'http://localhost:8080/api';
export const SERVER_URL = 'http://localhost:3000';
