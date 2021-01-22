export interface Book {
	id: number;
	cover: string;
	name: string;
	author: string;
	isbn: string;
	publishDate: string;
	price: number;
	bookAbstract: string;
}

export interface BookRequestParams {
	pageNo: number;
	pageSize: number;
}
