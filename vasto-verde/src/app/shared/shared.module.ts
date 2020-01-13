import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TextMaskModule } from 'angular2-text-mask';

import { CampocontrolerroComponent } from './mensagens-formulario/campo-control-erro/campo-control-erro.component';
import { ConfirmModalComponent } from './mensagens-formulario/confirm-modal/confirm-modal.component';

import { MascaraCpfPipe } from './services/pipes/mascara-cpf.pipe';
import { PrimeiroNomePipe } from './services/pipes/primeiro-nome.pipe';
import { CorFaixaPipe } from './services/pipes/cor-faixa.pipe';

import { ToastService } from './services/toast/toast.service';
import { ConfirmModalService } from './mensagens-formulario/confirm-modal/confirm-modal.service';
import { ValidacoesFormService } from './services/validacoes-form.service';

@NgModule({
  declarations: [
    CampocontrolerroComponent,
    ConfirmModalComponent,
    MascaraCpfPipe,
    PrimeiroNomePipe,
    CorFaixaPipe
  ],
  imports: [
    CommonModule,
    TextMaskModule
  ],
  exports: [
    CampocontrolerroComponent,
    MascaraCpfPipe,
    PrimeiroNomePipe,
    CorFaixaPipe,
    TextMaskModule
  ],
  providers: [
    ToastService,
    ValidacoesFormService,
    ConfirmModalService
  ],
  entryComponents: [
    ConfirmModalComponent
  ]
})
export class SharedModule { }
