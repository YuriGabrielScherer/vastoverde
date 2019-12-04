import { NgModule } from '@angular/core';
import { Routes, RouterModule, CanActivate } from '@angular/router';

// Componentes
import { IndexComponent } from './shared/index/index.component';
import { LoginComponent } from './login/login/login.component';

// Guards
import { AuthGuard } from './guards/auth-guard';

const routes: Routes = [
  { // Caso vazio, vai para o index
    path: '', pathMatch: 'full', redirectTo: 'index'
  },
  {
    path: 'index', component: IndexComponent
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'administrativo',
    loadChildren: () => import('./administrativo/administrativo.module').then(m => m.AdministrativoModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'pessoa',
    loadChildren: () => import('./pessoa/pessoa.module').then(m => m.PessoaModule)
  },
  {
    path: 'atleta',
    loadChildren: () => import('./atleta/atleta.module').then(m => m.AtletaModule)
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
