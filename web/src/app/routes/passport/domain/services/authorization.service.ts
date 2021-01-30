import { Injectable } from '@angular/core';
import { Params } from '@angular/router';
import { StandardResponse } from '@deepdraw/core';
import { AuthInfo, UserInfo } from '../models/passport.model';
import { PassportService } from './passport.service';

@Injectable({
	providedIn: 'root',
})
export class AuthorizationService {
	private authInfo: AuthInfo = null;
	redirectUrl: string;
	redirectQueryParams: Params;

	constructor(private passportService: PassportService) {}

	async hasAuthorized(): Promise<boolean> {
		if (this.authInfo && this.authInfo.userInfo) {
			return Promise.resolve(true);
		}
		// 真实项目把user信息存到cookie，不用每次刷新时再请求用户信息接口
		return new Promise((resolve) => {
			this.passportService.getUserInfo().subscribe((response: StandardResponse<UserInfo>) => {
				if (response.successful()) {
					this.setAuthInfo(response.body() as UserInfo);
					return resolve(true);
				} else {
					return resolve(false);
				}
			});
		});
	}

	setAuthInfo(userInfo: UserInfo) {
		this.authInfo = {
			...this.authInfo,
			userInfo,
		};
	}
}
