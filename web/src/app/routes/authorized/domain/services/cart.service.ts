import { Injectable } from '@angular/core';
import { BaseHttpService } from '../../../../shared/services/base-http.service';

@Injectable()
export class CartService {
	private bookUrl = '/api';

	constructor(private baseHttp: BaseHttpService) {}

	getCartDetails() {
		const url = `${this.bookUrl}/cart/details`;
		return this.baseHttp.get(url);
	}

	addToCart(bookId: number) {
		const url = `${this.bookUrl}/cart/item`;
		return this.baseHttp.postByPath(url, { bookId });
	}

	deleteCartItem(bookId: number) {
		const url = `${this.bookUrl}/cart/item`;
		return this.baseHttp.delete(url, { bookId });
	}
}
