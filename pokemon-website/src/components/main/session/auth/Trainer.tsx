import { hasRoles } from '#/Types/User';
import NotFound from '#/pages/404';
import { useContainerContext } from '../../view/Container';

export default function Trainer({ children }: { children?: JSX.Element[] | JSX.Element }) {
	const { heartbeatInfo } = useContainerContext();

	if (!hasRoles(heartbeatInfo.heartbeat, ['ROLE_ADMIN', 'ROLE_TRAINER'])) {
		return <NotFound />;
	}
	return <>{children}</>;
}
