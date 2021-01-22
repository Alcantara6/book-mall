import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TopBarComponent } from './components/top-bar/top-bar.component';
import { BookListComponent } from './components/book-list/book-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { zh_CN } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import zh from '@angular/common/locales/zh';
import {
	NzButtonModule,
  NzFormModule,
	NzIconModule,
  NzInputNumberModule,
	NzLayoutModule,
	NzMessageModule,
	NzModalModule,
	NzTableModule,
} from 'ng-zorro-antd';
import { INTERCEPTORS } from './shared/interceptors';
import { BookEditComponent } from './components/book-edit/book-edit.component';

registerLocaleData(zh);

@NgModule({
	declarations: [AppComponent, TopBarComponent, BookListComponent, BookEditComponent],
	imports: [
		BrowserModule,
		AppRoutingModule,
		FormsModule,
		ReactiveFormsModule,
		HttpClientModule,
		BrowserAnimationsModule,
		NzLayoutModule,
    NzTableModule,
    NzFormModule,
		NzButtonModule,
		NzIconModule,
		NzMessageModule,
    NzModalModule,
    NzInputNumberModule
	],
	providers: [{ provide: NZ_I18N, useValue: zh_CN }, INTERCEPTORS],
	bootstrap: [AppComponent],
})
export class AppModule {}
