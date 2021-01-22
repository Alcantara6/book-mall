import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class RequestUrlQueue {
	get queue(): string[] {
		return this.urlQueue;
	}

	private urlQueue: string[] = [];

	/** 将一个url压入请求url队列中 */
	push(newUrl: string) {
		this.urlQueue.push(newUrl);
	}

	/** 从请求url队列中移除一个url */
	remove(removingUrl: string) {
		this.urlQueue.splice(
			this.urlQueue.findIndex((url) => removingUrl.endsWith(url)),
			1,
		);
	}

	/** 判断请求url队列是否为空 */
	isEmpty() {
		return !this.urlQueue.length;
	}
}
