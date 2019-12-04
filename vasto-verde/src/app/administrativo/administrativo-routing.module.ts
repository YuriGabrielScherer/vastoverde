import { AtletaComponent } from './../atleta/atleta/atleta.component';
import { AuthGuard } from './../guards/auth-guard';
import { AdministrativoComponent } from './administrativo/administrativo.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


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
