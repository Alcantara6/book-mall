import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { DefaultInterceptor } from './default-interceptor';

export const INTERCEPTORS = [{ provide: HTTP_INTERCEPTORS, useClass: DefaultInterceptor, multi: true }];
