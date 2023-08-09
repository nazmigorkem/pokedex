import Container from '#/components/main/view/Container';
import Sidebar from '#/components/main/view/sidebar/Sidebar';
import '#/styles/globals.css';
import type { AppProps } from 'next/app';

export default function App({
	Component,
	pageProps,
}: {
	Component: AppProps['Component'] & { Layout?: ({ children }: { children: JSX.Element | JSX.Element[] }) => JSX.Element };
	pageProps: AppProps['pageProps'];
}) {
	return (
		<>
			<Container>
				{Component.Layout ? (
					<Component.Layout>
						<Sidebar />
						<div className="w-5/6 float-right min-h-screen">
							<Component {...pageProps} />
						</div>
					</Component.Layout>
				) : (
					<>
						<Sidebar />
						<div className="w-5/6 float-right min-h-screen">
							<Component {...pageProps} />
						</div>
					</>
				)}
			</Container>
		</>
	);
}
