import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CampocontrolerroComponent } from './mensagens-formulario/campo-control-erro/campo-control-erro.component';

@NgModule({
  declarations: [
    CampocontrolerroComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    CampocontrolerroComponent
  ]
})
export class SharedModule { }
