import { RoleResponse, UserAddRequest, UserEditRequest } from '#/Types/User';
import { Dispatch, SetStateAction } from 'react';

export default function RoleSelection({
	roles,
	properties,
	setProperties,
}: {
	roles?: RoleResponse[];
	properties: UserAddRequest | UserEditRequest;
	setProperties: Dispatch<SetStateAction<UserAddRequest>> | Dispatch<SetStateAction<UserEditRequest>>;
}) {
	return (
		<div>
			<label className="label">Roles</label>
			<div className="grid grid-cols-3 gap-5">
				{roles?.map((type) => (
					<button
						onClick={() => {
							if (properties.roles.length === 1 && properties.roles.some((x) => x.name === type.name)) return;

							const propertyClone = { ...properties };
							if (propertyClone?.roles.some((x) => x.name === type.name)) {
								propertyClone.roles = propertyClone.roles.filter((x) => x.name !== type.name);
							} else if (propertyClone?.roles.some((x) => x.name !== type.name)) {
								if (propertyClone.roles[0].name === 'ROLE_ANONYMOUS') {
									propertyClone.roles = [{ ...type }];
								} else {
									propertyClone.roles = [...propertyClone.roles, { ...type }];
								}
							}
							// @ts-ignore
							setProperties(propertyClone);
						}}
						key={type.name}
						className={`btn btn-accent px-3 py-1 rounded-md text-center truncate ${
							properties?.roles.some((x) => x.name === type.name) ? 'outline outline-2 outline-success outline-offset-2' : ''
						} ${
							properties.roles.length === 1 && properties.roles.some((x) => x.name === type.name)
								? 'no-animation hover:bg-accent cursor-default'
								: ''
						}`}
					>
						<span>{type.name}</span>
					</button>
				))}
			</div>
		</div>
	);
}
