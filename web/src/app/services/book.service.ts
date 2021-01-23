import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Book, BookRequestParams } from '../models/book.model';
import { BaseHttpService } from './base-http.service';

@Injectable({
	providedIn: 'root',
})
export class BookService {
	private bookUrl = '/api';

	constructor(private baseHttp: BaseHttpService) {}

	getBooks(pageNo: number, pageSize: number) {
    const params: BookRequestParams = {
      pageNo,
      pageSize
    }
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
