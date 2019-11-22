import { ListarAtletasComponent } from './listar-atletas/listar-atletas.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: 'cadastro', component: CadastroComponent
  },
  {
    path: 'listar', component: ListarAtletasComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AtletaRoutingModule { }
