import { BookStatus } from '../constants/book.constant';

export interface Book {
	id: number;
	cover: string;
	name: string;
	author: string;
	isbn: string;
	stock: number;
	publishDate: string;
	price: number;
	bookAbstract: string;
	status: BookStatus;
}

export interface BookRequestParams {
	pageNo: number;
	pageSize: number;
}
