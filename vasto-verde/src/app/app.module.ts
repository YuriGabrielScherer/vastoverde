import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { IndexComponent } from './shared/index/index.component';
import { HeaderComponent } from './shared/header/header.component';
import { FooterComponent } from './shared/footer/footer.component';
import { LoginComponent } from './login/login/login.component';

import { AdministrativoModule } from './administrativo/administrativo.module';
import { AppBootstrapModule } from './shared/app-bootstrap/app-bootstrap/app-bootstrap.module';

import { AuthGuard } from './guards/auth-guard';
import { AuthService } from './login/auth.service';

// Toast
import {
  ToastrModule,
  ToastNoAnimation,
  ToastNoAnimationModule
} from 'ngx-toastr';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    AdministrativoModule,
    AppBootstrapModule,
    ToastrModule.forRoot(
      // Configuracoes globais do Toast
      {
        timeOut: 3500,
        progressBar: true,
        closeButton: true
      }
    )
  ],
  providers: [AuthService, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
