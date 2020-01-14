import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TextMaskModule } from 'angular2-text-mask';
import { ReactiveFormsModule } from '@angular/forms';

import { CampoControlErroComponent } from './mensagens-formulario/campo-control-erro/campo-control-erro.component';
import { ConfirmModalComponent } from './mensagens-formulario/confirm-modal/confirm-modal.component';
import { EnviaEmailComponent } from './envia-email/envia-email.component';

import { MascaraCpfPipe } from './services/pipes/mascara-cpf.pipe';
import { PrimeiroNomePipe } from './services/pipes/primeiro-nome.pipe';
import { CorFaixaPipe } from './services/pipes/cor-faixa.pipe';

import { ToastService } from './services/toast/toast.service';
import { ConfirmModalService } from './mensagens-formulario/confirm-modal/confirm-modal.service';
import { ValidacoesFormService } from './services/validacoes-form.service';
import { EnviaEmailService } from './envia-email/envia-email.service';

@NgModule({
  declarations: [
    CampoControlErroComponent,
    ConfirmModalComponent,
    EnviaEmailComponent,
    MascaraCpfPipe,
    PrimeiroNomePipe,
    CorFaixaPipe,
  ],
  imports: [
    CommonModule,
    TextMaskModule,
    ReactiveFormsModule
  ],
  exports: [
    CampoControlErroComponent,
    MascaraCpfPipe,
    PrimeiroNomePipe,
    CorFaixaPipe,
    TextMaskModule
  ],
  providers: [
    ToastService,
    ValidacoesFormService,
    ConfirmModalService,
    EnviaEmailService
  ],
  entryComponents: [
    ConfirmModalComponent,
    EnviaEmailComponent
  ]
})
export class SharedModule { }
