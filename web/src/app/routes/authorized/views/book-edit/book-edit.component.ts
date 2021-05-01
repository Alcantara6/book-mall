import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BookStatus } from '../../domain/constants/book.constant';
import { Book } from '../../domain/models/book.model';

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
		const { id, cover, name, author, bookAbstract, isbn, stock, price, publishDate, status } = this.book || {};
		this.bookForm = this.formBuilder.group({
			id: [id || null, []],
			cover: [cover || '', []],
			name: [name || '', [Validators.required]],
			author: [author || '', [Validators.required]],
			bookAbstract: [bookAbstract || '', [Validators.required]],
			isbn: [isbn || '', [Validators.required]],
			stock: [stock || null, [Validators.required]],
			price: [price || null, [Validators.required]],
			// publishDate最好用date-fns格式化为YYYY-MM-dd格式
			publishDate: [publishDate || null, [Validators.required]],
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
