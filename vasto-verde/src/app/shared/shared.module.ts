import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CampocontrolerroComponent } from './mensagens-formulario/campo-control-erro/campo-control-erro.component';

import { ToastService } from './services/toast/toast.service';
import { ValidacoesFormService } from './services/validacoes-form.service';
import { MascaraCpfPipe } from './services/mascara-cpf.pipe';
import { PrimeiroNomePipe } from './services/primeiro-nome.pipe';

@NgModule({
  declarations: [
    CampocontrolerroComponent,
    MascaraCpfPipe,
    PrimeiroNomePipe
  ],
  imports: [
    CommonModule
  ],
  exports: [
    CampocontrolerroComponent,
    MascaraCpfPipe,
    PrimeiroNomePipe
  ],
  providers: [
    ToastService,
    ValidacoesFormService
  ]
})
export class SharedModule { }
