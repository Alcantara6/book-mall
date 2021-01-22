import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Book } from '../models/book.model';

const HTTP_OPTIONS = {
	headers: new HttpHeaders({
		'Content-Type': 'application/json',
	}),
};

@Injectable({
	providedIn: 'root',
})
export class BookService {
	private bookUrl = '/api';

	constructor(private http: HttpClient) {}

	getBooks() {
		const url = `${this.bookUrl}/books`;
		return this.http.get(url);
	}

	addBook(book: Book) {
		const url = `${this.bookUrl}/add`;
		return this.http.post(url, book, HTTP_OPTIONS);
	}

	editBook(book: Book) {
		const url = `${this.bookUrl}/edit`;
		return this.http.put(url, book, HTTP_OPTIONS);
	}

	deleteBook(bookId: number): Observable<any> {
		const url = `${this.bookUrl}/remove/${bookId}`;
		return this.http.delete(url, HTTP_OPTIONS);
	}
}
