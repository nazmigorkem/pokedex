export type UserHeartbeatResponse = {
	username: string;
	roles: Role[];
};

export type Role = 'ROLE_ADMIN' | 'ROLE_TRAINER';
