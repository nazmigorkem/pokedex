import { hasRoles } from '#/Types/User';
import { useRouter } from 'next/router';
import { useContainerContext } from '../../view/Container';

export default function Admin({ children }: { children?: JSX.Element[] | JSX.Element }) {
	const { heartbeatInfo } = useContainerContext();
	const router = useRouter();

	if (!hasRoles(heartbeatInfo.heartbeat, ['ROLE_ADMIN', 'ROLE_TRAINER'])) {
		router.push('/');
		return <></>;
	}
	return <>{children}</>;
}
