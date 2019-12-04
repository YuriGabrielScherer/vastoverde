import { AtletaService } from './atleta.service';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from './../shared/shared.module';
import { AtletaRoutingModule } from './atleta-routing.module';

import { AtletaComponent } from './atleta/atleta.component';
import { AtletaResolver } from './guard/atleta-resolver';

import { AtletaListarComponent } from './atleta-listar/atleta-listar.component';
import { AtletaFckComponent } from './atleta-fck/atleta-fck.component';

@NgModule({
  declarations: [
    AtletaComponent,
    AtletaListarComponent,
    AtletaFckComponent
  ],
  imports: [
    CommonModule,
    AtletaRoutingModule,
    SharedModule
  ],
  exports: [
    AtletaComponent
  ],
  providers: [
    AtletaResolver,
    AtletaService
  ]
})
export class AtletaModule { }
