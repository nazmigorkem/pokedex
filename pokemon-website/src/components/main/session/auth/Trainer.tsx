import { hasRoles } from '#/Types/User';
import Unauthorized from '#/pages/401';
import { useContainerContext } from '../../view/Container';

export default function Trainer({ children }: { children?: JSX.Element[] | JSX.Element }) {
	const { heartbeatInfo } = useContainerContext();

	if (!hasRoles(heartbeatInfo.heartbeat, ['ROLE_ADMIN', 'ROLE_TRAINER'])) {
		return <Unauthorized />;
	}
	return <>{children}</>;
}
