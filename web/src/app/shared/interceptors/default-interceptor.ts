import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {
	HttpInterceptor,
	HttpRequest,
	HttpHandler,
	HttpErrorResponse,
	HttpEvent,
	HttpResponse,
	HttpHeaders,
} from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { mergeMap, catchError } from 'rxjs/operators';
import { NzMessageService } from 'ng-zorro-antd';
import { StandardResponse } from '@deepdraw/core';
import { RequestUrlQueue } from './request-url-queue';
/**
 * 默认HTTP拦截器
 */
@Injectable()
export class DefaultInterceptor implements HttpInterceptor {
	constructor(private router: Router, private messageService: NzMessageService, private urlQueue: RequestUrlQueue) {}

	private handleErrorResponse(error: HttpErrorResponse): Observable<any> {
		this.urlQueue.remove(error.url);
		if (this.urlQueue.isEmpty()) {
			switch (error.status) {
				case 401:
					this.messageService.warning('请先登录');
					this.router.navigateByUrl('passport/login');
					break;
				case 403:
					this.messageService.warning('无权查看');
					break;
				case 404:
					this.messageService.warning('参数错误');
					break;
				case 500:
					this.messageService.warning('网络异常');
					break;
				default:
					this.messageService.warning('网络异常');
					break;
			}
		}
		return of(
			new HttpResponse({ body: new StandardResponse<any>({ message: 'UNKNOWN', code: 'UNKNOWN', body: null }) }),
		);
	}

	isGetI18nFileRequest(e: HttpResponse<any>) {
		const reg = /.*i18n\/.*.json/;
		return reg.test(e.url);
	}

	intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
		req = req.clone({
			withCredentials: true,
			headers: new HttpHeaders({
				'X-Requested-With': 'XMLHttpRequest',
			}),
		});

		this.urlQueue.push(req.url);

		return next.handle(req).pipe(
			mergeMap((e: any) => {
				if (e instanceof HttpResponse) {
					this.urlQueue.remove(e.url);
					if (!this.isGetI18nFileRequest(e)) {
						const httpBody = new StandardResponse<any>(e.body);
						const event = new HttpResponse({
							body: httpBody,
							headers: e.headers,
							status: e.status,
							statusText: e.statusText,
							url: e.url,
						});
						return of(event);
					}
				}
				return of(e);
			}),
			catchError((error: HttpErrorResponse) => this.handleErrorResponse(error)),
		);
	}
}
