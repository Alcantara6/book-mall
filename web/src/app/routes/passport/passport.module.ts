import { NgModule } from '@angular/core';
import { PassportRoutingModule } from './passport-routing.module';
import { LoginComponent } from './views/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NzFormModule, NzInputModule } from 'ng-zorro-antd';
import { SharedModule } from 'src/app/shared/shared.module';
import { PassportService } from './domain/services/passport.service';

@NgModule({
	declarations: [LoginComponent],
	imports: [SharedModule, PassportRoutingModule, FormsModule, ReactiveFormsModule, NzFormModule, NzInputModule],
	providers: [PassportService],
})
export class PassportModule {}
