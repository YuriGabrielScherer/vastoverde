import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AtletaRoutingModule } from './atleta-routing.module';
import { CadastroComponent } from './cadastro/cadastro.component';
import { ListarAtletasComponent } from './listar-atletas/listar-atletas.component';

import { SharedModule } from './../shared/shared.module';


@NgModule({
  declarations: [
    CadastroComponent,
    ListarAtletasComponent],
  imports: [
    CommonModule,
    AtletaRoutingModule,
    SharedModule
  ]
})
export class AtletaModule { }
