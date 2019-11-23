import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CampocontrolerroComponent } from './mensagens-formulario/campo-control-erro/campo-control-erro.component';

import { ToastService } from './services/toast/toast.service';

@NgModule({
  declarations: [
    CampocontrolerroComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    CampocontrolerroComponent
  ],
  providers: [
    ToastService
  ]
})
export class SharedModule { }
