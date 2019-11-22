import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AtletaRoutingModule } from './atleta-routing.module';
import { CadastroComponent } from './cadastro/cadastro.component';


@NgModule({
  declarations: [CadastroComponent],
  imports: [
    CommonModule,
    AtletaRoutingModule
  ]
})
export class AtletaModule { }
