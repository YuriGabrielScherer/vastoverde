import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AdministrativoComponent } from './administrativo/administrativo.component';
import { AuthGuard } from './../guards/auth-guard';

const routes: Routes = [
  {
    path: '', component: AdministrativoComponent, children: [
      {
        path: 'atleta',
        loadChildren: () => import('../atleta/atleta.module').then(m => m.AtletaModule),
        canActivate: [AuthGuard]
      }

    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdministrativoRoutingModule { }
