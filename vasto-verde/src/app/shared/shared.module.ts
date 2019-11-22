import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ToastService } from './toast/toast.service';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ToastService
  ],
  exports: [ToastService]
})
export class SharedModule { }
