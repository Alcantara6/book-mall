import { NgModule } from '@angular/core';
import { AuthorizedRoutingModule } from './authorized-routing.module';
import { AuthorizedComponent } from './authorized.component';
import { NzFormModule, NzInputNumberModule, NzLayoutModule, NzTableModule } from 'ng-zorro-antd';
import { BookEditComponent } from './views/book-edit/book-edit.component';
import { BookListComponent } from './views/book-list/book-list.component';
import { TopBarComponent } from './views/top-bar/top-bar.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BookService } from './domain/services/book.service';
import { CartService } from './domain/services/cart.service';
import { CartComponent } from './views/cart/cart.component';
import { CartState } from './domain/states/cart.state';

@NgModule({
	declarations: [AuthorizedComponent, TopBarComponent, BookListComponent, BookEditComponent, CartComponent],
	imports: [
		SharedModule,
		AuthorizedRoutingModule,
		NzTableModule,
		NzFormModule,
		FormsModule,
		ReactiveFormsModule,
		NzLayoutModule,
		NzInputNumberModule,
	],
	providers: [CartState, BookService, CartService],
})
export class AuthorizedModule {}
