import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ListarAtletasComponent } from './listar-atletas/listar-atletas.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { AuthGuard } from './../guards/auth-guard';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'cadastro' },
  {
    path: 'cadastro', component: CadastroComponent,

  },
  {
    path: 'listar', component: ListarAtletasComponent, canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AtletaRoutingModule { }
