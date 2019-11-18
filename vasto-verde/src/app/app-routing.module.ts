import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { IndexComponent } from './shared/index/index.component';
import { AuthGuard } from './guards/auth-guard';

const routes: Routes = [
  { // Caso vazio, vai para o index
    path: '', pathMatch: 'full', redirectTo: 'index'
  },
  {
    path: 'index', component: IndexComponent
  },
  {
    path: 'login',
    loadChildren: () => import('./login/login.module').then(m => m.LoginModule)
  },
  {
    path: 'administrativo',
    loadChildren: () => import('./administrativo/administrativo.module').then(m => m.AdministrativoModule),
    canActivate: [AuthGuard]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
