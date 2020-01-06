import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TextMaskModule } from 'angular2-text-mask';


import { ValidacoesFormService } from './services/validacoes-form.service';
import { MascaraCpfPipe } from './services/pipes/mascara-cpf.pipe';
import { PrimeiroNomePipe } from './services/pipes/primeiro-nome.pipe';
import { ToastService } from './services/toast/toast.service';
import { CampocontrolerroComponent } from './mensagens-formulario/campo-control-erro/campo-control-erro.component';
import { ConfirmModalComponent } from './mensagens-formulario/confirm-modal/confirm-modal.component';
import { ConfirmModalService } from './mensagens-formulario/confirm-modal/confirm-modal.service';

@NgModule({
  declarations: [
    CampocontrolerroComponent,
    MascaraCpfPipe,
    PrimeiroNomePipe,
    ConfirmModalComponent
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
    ValidacoesFormService,
    ConfirmModalService
  ],
  entryComponents: [
    ConfirmModalComponent
  ]
})
export class SharedModule { }
