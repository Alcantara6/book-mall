import { AuthorizedComponent } from './authorized.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BookListComponent } from './views/book-list/book-list.component';

const routes: Routes = [
	{
		path: '',
		component: AuthorizedComponent,
		children: [
			{
				path: '',
				redirectTo: 'books',
			},
			{
				path: 'books',
				component: BookListComponent,
			},
		],
	},
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule],
})
export class AuthorizedRoutingModule {}
