import { Injectable } from '@angular/core';
import { StandardResponse } from '@deepdraw/core';
import { BehaviorSubject, Subject } from 'rxjs';
import { Book } from '../models/book.model';
import { CartDetails, CartItem } from '../models/cart.model';
import { CartService } from '../services/cart.service';

@Injectable()
export class CartState {
	cartDetails$: Subject<CartDetails> = new BehaviorSubject(null);

	constructor(private cartService: CartService) {}

	getCartItems() {
		this.cartService.getCartDetails().subscribe((response: StandardResponse<CartDetails>) => {
			this.onHandlerUpdateCartDetails(response);
		});
	}

	deleteCartItem(item: CartItem) {
		this.cartService.deleteCartItem(item.book.id).subscribe((response: StandardResponse<CartDetails>) => {
			this.onHandlerUpdateCartDetails(response);
		});
	}

	addToCart(book: Book): void {
		this.cartService.addToCart(book.id).subscribe((response: StandardResponse<CartDetails>) => {
			this.onHandlerUpdateCartDetails(response);
		});
	}

	private onHandlerUpdateCartDetails(response: StandardResponse<CartDetails>) {
		if (response.successful()) {
			const details = response.body() as CartDetails;
			this.cartDetails$.next({
				...details,
				totalCount: this.totalCounts(details),
			});
		}
	}

	private totalCounts(details: CartDetails) {
		return details.items.length ? details.items.reduce((prev, curr) => prev + curr.quantity, 0) : 0;
	}
}
