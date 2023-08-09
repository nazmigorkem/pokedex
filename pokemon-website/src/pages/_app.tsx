import Container from '#/components/main/view/Container';
import Sidebar from '#/components/main/view/sidebar/Sidebar';
import '#/styles/globals.css';
import type { AppProps } from 'next/app';

export default function App({
	Component,
	pageProps,
}: {
	Component: AppProps['Component'] & { Auth?: ({ children }: { children: JSX.Element | JSX.Element[] }) => JSX.Element };
	pageProps: AppProps['pageProps'];
}) {
	return (
		<>
			<Container>
				<Sidebar />
				<div className="w-5/6 float-right min-h-screen">
					{Component.Auth ? (
						<>
							<Component.Auth>
								<Component {...pageProps} />
							</Component.Auth>
						</>
					) : (
						<>
							<Component {...pageProps} />
						</>
					)}
				</div>
			</Container>
		</>
	);
}
