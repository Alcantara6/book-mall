import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { BookStatus } from 'src/app/constants/book.constant';
import { Book } from 'src/app/models/book.model';

@Component({
	selector: 'app-book-edit',
	templateUrl: './book-edit.component.html',
	styleUrls: ['./book-edit.component.less'],
})
export class BookEditComponent implements OnInit, OnChanges {
	bookForm: FormGroup;

	@Input() title: string;
	@Input() book: Book;
	@Input() isVisible: boolean;
	@Output() canceled: EventEmitter<any> = new EventEmitter();
	@Output() confirm: EventEmitter<Book> = new EventEmitter();

	isOkLoading = false;

	constructor(private formBuilder: FormBuilder) {}

	ngOnChanges(changes: SimpleChanges) {
		if (changes.book) {
			this.createForm();
		}
	}

	ngOnInit() {
		this.createForm();
	}

	createForm() {
		const { id, name, author, bookAbstract, isbn, stock, price, publishDate, status } = this.book || {};
		this.bookForm = this.formBuilder.group({
			id: [id || null, []],
			name: [name || '', []],
			author: [author || '', []],
			bookAbstract: [bookAbstract || '', []],
			isbn: [isbn || '', []],
			stock: [stock || null, []],
			price: [price || null, []],
			publishDate: [publishDate || null, []],
			status: [status || BookStatus.AVAILABLE, []],
		});
	}

	submitForm() {
		this.confirm.emit(this.bookForm.value);
	}

	resetForm() {
		this.bookForm.reset();
	}

	handleCancel(): void {
		this.canceled.emit();
	}
}
