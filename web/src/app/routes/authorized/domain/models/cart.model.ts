import { Book } from './book.model';

export interface CartDetails {
	totalPrice: number;
	totalCount: number;
	items: CartItem[];
}

export interface CartItem {
	book: Book;
	/** 单项数量 */
	quantity: number;
	/** 单项价格小计 */
	subTotal: number;
}
