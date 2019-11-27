import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PessoaRoutingModule } from './pessoa-routing.module';
import { CadastroComponent } from './cadastro/cadastro.component';
import { ListarPessoasComponent } from './listar-pessoas/listar-pessoas.component';

import { SharedModule } from '../shared/shared.module';
import { TextMaskModule } from 'angular2-text-mask';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';

import { ListarPessoaResolver } from './guards/listar-pessoa.resolver';

@NgModule({
  declarations: [
    CadastroComponent,
    ListarPessoasComponent
  ],
  imports: [
    CommonModule,
    PessoaRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    FormsModule,
    TextMaskModule,
    BsDatepickerModule.forRoot()
  ],
  providers: [
    ListarPessoaResolver
  ]
})
export class PessoaModule { }
