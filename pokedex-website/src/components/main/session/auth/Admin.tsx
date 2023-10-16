import { hasRoles } from '#/Types/User';
import Forbidden from '#/pages/403';
import { useContainerContext } from '../../view/Container';

export default function Admin({ children }: { children?: JSX.Element[] | JSX.Element }) {
	const { heartbeatInfo } = useContainerContext();

	if (!hasRoles(heartbeatInfo.heartbeat, ['ROLE_ADMIN'])) {
		return <Forbidden />;
	}
	return <>{children}</>;
}
