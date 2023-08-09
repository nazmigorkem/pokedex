export type UserHeartbeatResponse = {
	username: string;
	roles: Role[];
};

export type UserSearchResponse = {
	username: string;
	roles: Role[];
};

export type UserResponse = {
	username: string;
	roles: RoleResponse[];
	catchList: PokemonResponse[];
	wishlist: PokemonResponse[];
};

export type UserAddRequest = {
	username: string;
	password: string;
	roles: Role[];
};

export type RoleResponse = {
	name: Role;
};

export type Role = (typeof ROLES)[keyof typeof ROLES];

export const ROLES = {
	admin: 'ROLE_ADMIN',
	trainer: 'ROLE_TRAINER',
	anonymous: 'ROLE_ANONYMOUS',
} as const;

export function hasRoles(user: UserHeartbeatResponse, roles: Role[]) {
	return roles.some((role) => user.roles.includes(role));
}
