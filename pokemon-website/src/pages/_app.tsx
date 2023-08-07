import Container from '#/components/main/view/Container';
import Sidebar from '#/components/main/view/Sidebar';
import '#/styles/globals.css';
import type { AppProps } from 'next/app';

export default function App({ Component, pageProps }: AppProps) {
	return (
		<>
			<Container>
				<Sidebar />
				<Component {...pageProps} />
			</Container>
		</>
	);
}
