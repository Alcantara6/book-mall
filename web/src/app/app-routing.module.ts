import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
	{
		path: 'authorized',
		loadChildren: () => import('./routes/authorized/authorized.module').then((mod) => mod.AuthorizedModule),
	},
	{
		path: 'passport',
		loadChildren: () => import('./routes/passport/passport.module').then((mod) => mod.PassportModule),
	},
	{
		path: '**',
		redirectTo: 'authorized',
		pathMatch: 'full',
	},
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule],
})
export class AppRoutingModule {}
