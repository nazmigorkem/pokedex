import Context from '#/components/main/view/Context';
import Sidebar from '#/components/main/view/Sidebar';
import '#/styles/globals.css';
import type { AppProps } from 'next/app';

export default function App({ Component, pageProps }: AppProps) {
	return (
		<>
			<Sidebar />
			<Context>
				<Component {...pageProps} />
			</Context>
		</>
	);
}
