import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CampocontrolerroComponent } from './mensagens-formulario/campo-control-erro/campo-control-erro.component';

import { ToastService } from './services/toast/toast.service';
import { ValidacoesFormService } from './services/validacoes-form.service';
import { MascaraCpfPipe } from './services/pipes/mascara-cpf.pipe';
import { PrimeiroNomePipe } from './services/pipes/primeiro-nome.pipe';
import { TextMaskModule } from 'angular2-text-mask';

@NgModule({
  declarations: [
    CampocontrolerroComponent,
    MascaraCpfPipe,
    PrimeiroNomePipe
  ],
  imports: [
    CommonModule,
    TextMaskModule
  ],
  exports: [
    CampocontrolerroComponent,
    MascaraCpfPipe,
    PrimeiroNomePipe,
    TextMaskModule
  ],
  providers: [
    ToastService,
    ValidacoesFormService
  ]
})
export class SharedModule { }
