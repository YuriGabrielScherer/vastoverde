import { AtletaResolver } from './guard/atleta-resolver';
import { AtletaListarComponent } from './atleta-listar/atleta-listar.component';
import { AtletaComponent } from './atleta/atleta.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule, Resolve } from '@angular/router';


const routes: Routes = [
  {
    path: '',
    component: AtletaComponent,
    resolve: { atleta: AtletaResolver }
  },
  {
    path: 'listar',
    component: AtletaListarComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AtletaRoutingModule { }
