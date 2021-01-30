import { Injectable } from '@angular/core';
import { BaseHttpService } from 'src/app/shared/services/base-http.service';
import { PassportModule } from '../../passport.module';
import { LoginFormParams } from '../models/passport.model';

@Injectable({
	providedIn: 'root',
})
export class PassportService {
	private accountUrl = '/api';

	constructor(private http: BaseHttpService) {}

	login(formBody: LoginFormParams) {
		const url = `${this.accountUrl}/user/login`;
		return this.http.postJson(url, formBody);
	}

	getUserInfo() {
		const url = `${this.accountUrl}/user/info`;
		return this.http.get(url);
	}
}
