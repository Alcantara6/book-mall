import { Injectable } from '@angular/core';
import {
	CanActivate,
	CanActivateChild,
	ActivatedRouteSnapshot,
	RouterStateSnapshot,
	Router,
	Params
} from '@angular/router';
import { AuthorizationService } from '../services/authorization.service';

@Injectable({
	providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanActivateChild {

	constructor(
		private router: Router,
		private authorizationService: AuthorizationService,
	) { }

	async canActivate(
		next: ActivatedRouteSnapshot,
		state: RouterStateSnapshot): Promise<boolean> {
		const path = next.routeConfig.path;
		return await this.checkLogin(path, next.queryParams);
	}

	async canActivateChild(
		next: ActivatedRouteSnapshot,
		state: RouterStateSnapshot): Promise<boolean> {
		const path = next.routeConfig.path;
		return await this.checkLogin(path, next.queryParams);
	}

	async checkLogin(path: string, queryParams: Params) {
		if (await this.authorizationService.hasAuthorized()) {
			return true;
		}
		this.setReDirectUrl(path, queryParams);
		this.router.navigateByUrl('passport/login');
		return false;
	}

	setReDirectUrl(path: string, queryParams: Params) {
		this.authorizationService.redirectUrl = path;
		this.authorizationService.redirectQueryParams = queryParams;
	}
}
