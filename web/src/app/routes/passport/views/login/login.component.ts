import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { StandardResponse } from '@deepdraw/core';
import { NzMessageService } from 'ng-zorro-antd';
import { UserInfo } from '../../domain/models/passport.model';
import { PassportService } from '../../domain/services/passport.service';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.less'],
})
export class LoginComponent implements OnInit {
	loginForm: FormGroup;

	constructor(
		private formBuilder: FormBuilder,
		private router: Router,
		private message: NzMessageService,
		private passportService: PassportService,
	) {}

	ngOnInit(): void {
		this.loginForm = this.formBuilder.group({
			username: [null, Validators.required],
			password: [null, Validators.required],
		});
	}

	submitForm() {
		this.validateForm();
		if (this.loginForm.valid) {
			this.requestLogin();
		}
	}

	validateForm() {
		for (const index of Object.keys(this.loginForm.controls)) {
			this.loginForm.controls[index].markAsDirty;
			this.loginForm.controls[index].updateValueAndValidity;
		}
	}

	requestLogin() {
		this.passportService.login(this.loginForm.value).subscribe((response: StandardResponse<UserInfo>) => {
			if (response.successful()) {
				this.router.navigate(['/authorized']);
			} else {
				this.message.error('登录失败！');
			}
		});
	}
}
