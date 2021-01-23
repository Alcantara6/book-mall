import { Component, OnInit } from '@angular/core';
import { StandardResponse } from '@deepdraw/core';
import { BookStatus } from 'src/app/constants/book.constant';
import { Book } from 'src/app/models/book.model';
import { Page } from 'src/app/models/response.model';
import { BookService } from 'src/app/services/book.service';
import { CartService } from 'src/app/services/cart.service';

@Component({
	selector: 'app-book-list',
	templateUrl: './book-list.component.html',
	styleUrls: ['./book-list.component.less'],
})
export class BookListComponent implements OnInit {
	readonly bookStatus = BookStatus;

	books: Book[] = [];
	totalCount = 1;
	pageNo = 1;
	pageSize = 5;

	currBook: Book = null;
	isShowModal = false;
	modalTitle: string;

	constructor(
    private bookService: BookService,
    private cartService: CartService,
  ) {}

	ngOnInit(): void {
		this.getBooks();
	}

	getBooks(): void {
		// pageNo后端从0开始
		this.bookService.getBooks(this.pageNo - 1, this.pageSize).subscribe((response: StandardResponse<Page<Book>>) => {
			const resBody = <Page<Book>>response.body();
			this.books = resBody.content;
			this.totalCount = resBody.totalElements;
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
    this.cartService.addToCart(book.id).subscribe(() => {
      this.getBooks();
    });
  }
}
