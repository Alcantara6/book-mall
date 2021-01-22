import { Component, OnInit } from '@angular/core';
import { PaginationBody, StandardResponse } from '@deepdraw/core';
import { Book } from 'src/app/models/book.model';
import { Page } from 'src/app/models/response.model';
import { BookService } from 'src/app/services/book.service';

@Component({
	selector: 'app-book-list',
	templateUrl: './book-list.component.html',
	styleUrls: ['./book-list.component.less'],
})
export class BookListComponent implements OnInit {
	books: Book[] = [];
	totalCount = 1;
	pageIndex = 1;
	pageSize = 10;

	currBook: Book = null;
	isShowModal = false;
	modalTitle: string;

	constructor(private bookService: BookService) {}

	ngOnInit(): void {
		this.getBooks();
	}

	getBooks(): void {
		this.bookService.getBooks().subscribe((response: StandardResponse<Page<Book>>) => {
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
		if (this.isEditBook(book)) {
			this.books = this.books.map((item) => (item.id === book.id ? book : item));
			this.confirmEditBook(book);
		} else {
			this.confirmAddBook(book);
		}
	}

	isEditBook(book: Book) {
		return this.books.find((item) => item.id === book.id);
	}

	confirmAddBook(book: Book): void {
		this.bookService.addBook(book as Book).subscribe((response: StandardResponse<Book>) => {
			this.books = [book, ...this.books];
			this.isShowModal = false;
		});
	}

	confirmEditBook(book: Book): void {
		this.bookService.editBook(book).subscribe(() => {
			this.isShowModal = false;
		});
	}

	deleteBook(bookId: number): void {
		this.books = this.books.filter((item) => item.id !== bookId);
		this.bookService.deleteBook(bookId).subscribe();
	}
}
