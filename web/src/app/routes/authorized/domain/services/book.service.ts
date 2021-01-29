import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { BaseHttpService } from '../../../../shared/services/base-http.service';
import { AuthorizedModule } from '../../authorized.module';
import { Book, BookRequestParams } from '../models/book.model';

@Injectable()
export class BookService {
	private bookUrl = '/api';

	constructor(private baseHttp: BaseHttpService) {}

	getBooks(pageNo: number, pageSize: number) {
		const params: BookRequestParams = {
			pageNo,
			pageSize,
		};
		const url = `${this.bookUrl}/mall/books`;
		return this.baseHttp.get(url, params);
	}

	addBook(book: Book) {
		const url = `${this.bookUrl}/mall/books`;
		return this.baseHttp.post(url, book);
	}

	editBook(book: Book) {
		const url = `${this.bookUrl}/mall/books/${book.id}`;
		return this.baseHttp.putJson(url, book);
	}

	deleteBook(bookId: number): Observable<any> {
		const url = `${this.bookUrl}/mall/books/${bookId}`;
		return this.baseHttp.delete(url);
	}

	toggleBookStatus(bookId: number): Observable<any> {
		const url = `${this.bookUrl}/admin/book/status/${bookId}`;
		return this.baseHttp.get(url);
	}
}
