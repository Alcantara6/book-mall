import { AuthorizedComponent } from './authorized.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BookListComponent } from './views/book-list/book-list.component';
import { CartComponent } from './views/cart/cart.component';
import { AuthGuard } from '../passport/domain/guards/auth-guard';

const routes: Routes = [
	{
		path: '',
		component: AuthorizedComponent,
		canActivate: [AuthGuard],
		canActivateChild: [AuthGuard],
		children: [
			{
				path: '',
				redirectTo: 'books',
			},
			{
				path: 'books',
				component: BookListComponent,
			},
			{
				path: 'cart',
				component: CartComponent,
			},
		],
	},
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule],
})
export class AuthorizedRoutingModule {}
