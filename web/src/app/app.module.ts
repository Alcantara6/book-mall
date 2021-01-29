import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { zh_CN } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import zh from '@angular/common/locales/zh';
import { INTERCEPTORS } from './shared/interceptors';
import { NzMessageModule, NzMessageService } from 'ng-zorro-antd';

registerLocaleData(zh);

@NgModule({
	declarations: [AppComponent],
	imports: [BrowserModule, AppRoutingModule, HttpClientModule, BrowserAnimationsModule, NzMessageModule],
	providers: [{ provide: NZ_I18N, useValue: zh_CN }, INTERCEPTORS],
	bootstrap: [AppComponent],
})
export class AppModule {}
