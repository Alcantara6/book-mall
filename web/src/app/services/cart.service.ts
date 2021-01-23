import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseHttpService } from './base-http.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {
	private bookUrl = '/api';

  constructor(private baseHttp: BaseHttpService) { }

  addToCart(bookId: number): Observable<any> {
    const url = `${this.bookUrl}/shopping/add/${bookId}`;
    return this.baseHttp.get(url);
  }
}
