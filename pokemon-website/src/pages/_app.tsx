import Container from '#/components/main/view/Container';
import Sidebar from '#/components/main/view/sidebar/Sidebar';
import '#/styles/globals.css';
import type { AppProps } from 'next/app';

export default function App({ Component, pageProps }: AppProps) {
	return (
		<>
			<Container>
				<Sidebar />
				<div className="w-5/6 float-right min-h-screen">
					<Component {...pageProps} />
				</div>
			</Container>
		</>
	);
}
