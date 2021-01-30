import { CartState } from './../../domain/states/cart.state';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { StandardResponse } from '@deepdraw/core';
import { BookService } from 'src/app/routes/authorized/domain/services/book.service';
import { CartService } from 'src/app/routes/authorized/domain/services/cart.service';
import { BookStatus } from '../../domain/constants/book.constant';
import { Book } from '../../domain/models/book.model';
import { Page } from '../../domain/models/response.model';
import { Subscription } from 'rxjs';
import { CartDetails } from '../../domain/models/cart.model';

@Component({
	selector: 'app-book-list',
	templateUrl: './book-list.component.html',
	styleUrls: ['./book-list.component.less'],
})
export class BookListComponent implements OnInit, OnDestroy {
	readonly bookStatus = BookStatus;

	books: Book[] = [];
	totalCount = 1;
	pageNo = 1;
	pageSize = 5;

	currBook: Book = null;
	isShowModal = false;
	modalTitle: string;

	cartDetails: CartDetails;
	cartDetailsSubscription: Subscription;

	constructor(private bookService: BookService, private cartState: CartState) {}

	ngOnInit(): void {
		this.subscribeCartDetails();
		this.cartState.getCartItems();
		this.getBooks();
	}

	ngOnDestroy(): void {
		this.unSubscribeCartDetails();
	}

	getBooks(): void {
		// pageNo后端从0开始
		this.bookService.getBooks(this.pageNo - 1, this.pageSize).subscribe((response: StandardResponse<Page<Book>>) => {
			if (response.successful()) {
				const resBody = response.body() as Page<Book>;
				this.books = resBody.content;
				this.totalCount = resBody.totalElements;
			}
		});
	}

	addBook(): void {
		this.currBook = null;
		this.modalTitle = '添加书籍';
		this.isShowModal = true;
	}

	editBook(book: Book): void {
		this.modalTitle = '编辑书籍';
		this.currBook = book;
		this.isShowModal = true;
	}

	confirm(book: Book): void {
		if (this.isEditBook()) {
			this.confirmEditBook(book);
		} else {
			this.confirmAddBook(book);
		}
	}

	isEditBook() {
		return this.currBook;
	}

	confirmAddBook(book: Book): void {
		this.bookService.addBook(book as Book).subscribe((response: StandardResponse<Book>) => {
			this.isShowModal = false;
			this.getBooks();
		});
	}

	confirmEditBook(book: Book): void {
		this.bookService.editBook(book).subscribe(() => {
			this.isShowModal = false;
			this.getBooks();
		});
	}

	deleteBook(bookId: number): void {
		this.bookService.deleteBook(bookId).subscribe(() => {
			this.getBooks();
		});
	}

	toggleStatus(book: Book): void {
		this.bookService.toggleBookStatus(book.id).subscribe(() => {
			this.getBooks();
		});
	}

	addToCart(book: Book): void {
		this.cartState.addToCart(book);
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
