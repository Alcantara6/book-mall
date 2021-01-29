import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { CartDetails, CartItem } from '../../domain/models/cart.model';
import { CartState } from '../../domain/states/cart.state';

@Component({
	selector: 'app-cart',
	templateUrl: './cart.component.html',
	styleUrls: ['./cart.component.less'],
})
export class CartComponent implements OnInit, OnDestroy {
	cartDetails: CartDetails;
	cartDetailsSubscription: Subscription;

	constructor(private cartState: CartState) {}

	ngOnInit(): void {
		this.subscribeCartDetails();
		this.cartState.getCartItems();
	}

	ngOnDestroy(): void {
		this.unSubscribeCartDetails();
	}

	deleteCartItem(item: CartItem) {
		this.cartState.deleteCartItem(item);
	}

	subscribeCartDetails() {
		this.cartDetailsSubscription = this.cartState.cartDetails$.subscribe((cartDetails) => {
			this.cartDetails = cartDetails;
		});
	}

	unSubscribeCartDetails() {
		this.cartDetailsSubscription && this.cartDetailsSubscription.unsubscribe();
	}
}
