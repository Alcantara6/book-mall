export interface LoginFormParams {
	username: string;
	password: string;
}

export interface UserInfo {
	username: string;
	email: string;
}

export interface AuthInfo {
	userInfo: UserInfo;
}
