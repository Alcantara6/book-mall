import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class BaseHttpService {
	constructor(public http: HttpClient) {}

	get(url: string, params: any = {}) {
		const options = {
			headers: this.getHttpOptions(),
			params: new HttpParams({ fromObject: params }),
		};
		return this.http.get(url, options);
	}

	getBody(url: string, params: any = {}) {
		return this.http.get(url, { headers: this.getHttpOptions(), params });
	}

	getByJson(url: string, params: any = {}) {
		return this.http.get(url, {
			headers: new HttpHeaders({
				'Content-Type': 'application/json',
			}),
			params,
		});
	}

	delete(url: string, params: any = '') {
		return this.http.delete(url, { headers: this.getHttpOptions(), params });
	}

	post(url: string, params?: any) {
    const reqParams = new HttpParams({ fromObject: params });
		return this.http.post(url, reqParams, { headers: this.getHttpOptions() });
	}

	postJson(url: string, params: any) {
		return this.http.post(url, params, { headers: this.getHttpOptions() });
	}

	postByPath(url: string, params: any) {
		return this.http.post(url, '', { headers: this.getHttpOptions(), params });
	}

	putByPath(url: string, params: any) {
		return this.http.put(url, '', { headers: this.getHttpOptions(), params });
	}

	put(url: string, params: any) {
		const reqParams = new HttpParams({ fromObject: params });
		return this.http.put(url, reqParams, { headers: this.getHttpOptions() });
	}

	putJson(url: string, params: any) {
		return this.http.put(url, params, { headers: this.getHttpOptions() });
	}

	postForRequestBody(url: string, params: any) {
		return this.http.post(url, JSON.stringify(params), { headers: this.getHttpOptions() });
	}

	getHttpOptions() {
		return new HttpHeaders({
			key: '',
			secret: '',
		});
	}
}
