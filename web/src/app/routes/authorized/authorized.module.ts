import { NgModule } from '@angular/core';
import { AuthorizedRoutingModule } from './authorized-routing.module';
import { AuthorizedComponent } from './authorized.component';
import { NzInputNumberModule, NzLayoutModule } from 'ng-zorro-antd';
import { BookEditComponent } from './views/book-edit/book-edit.component';
import { BookListComponent } from './views/book-list/book-list.component';
import { TopBarComponent } from './views/top-bar/top-bar.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
	declarations: [AuthorizedComponent, TopBarComponent, BookListComponent, BookEditComponent],
	imports: [
		SharedModule,
		AuthorizedRoutingModule,
		FormsModule,
		ReactiveFormsModule,
		NzLayoutModule,
		NzInputNumberModule,
	],
})
export class AuthorizedModule {}
