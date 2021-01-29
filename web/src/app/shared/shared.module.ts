import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
	NzButtonModule,
	NzFormModule,
	NzIconModule,
	NzMessageModule,
	NzModalModule,
	NzTableModule,
} from 'ng-zorro-antd';

@NgModule({
	declarations: [],
	exports: [CommonModule, NzButtonModule, NzIconModule, NzMessageModule, NzModalModule],
})
export class SharedModule {}
