import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseHttpService } from '../../../../shared/services/base-http.service';
import { AuthorizedModule } from '../../authorized.module';

@Injectable()
export class CartService {
	private bookUrl = '/api';

	constructor(private baseHttp: BaseHttpService) {}

	addToCart(bookId: number): Observable<any> {
		const url = `${this.bookUrl}/cart/item`;
		return this.baseHttp.postByPath(url, { bookId });
	}
}