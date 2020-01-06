import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { IndexComponent } from './shared/index/index.component';
import { HeaderComponent } from './shared/header/header.component';
import { FooterComponent } from './shared/footer/footer.component';
import { LoginComponent } from './login/login/login.component';

import { AdministrativoModule } from './administrativo/administrativo.module';
import { AppBootstrapModule } from './shared/app-bootstrap/app-bootstrap.module';
import { PessoaModule } from './pessoa/pessoa.module';
import { SharedModule } from './shared/shared.module';

import { AuthGuard } from './guards/auth-guard';
import { AuthService } from './login/auth.service';

import { TextMaskModule } from 'angular2-text-mask';
// NGX Bootrstrap
import { ToastrModule } from 'ngx-toastr';
import { ModalModule } from 'ngx-bootstrap/modal';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


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
    HttpClientModule,
    AdministrativoModule,
    AppBootstrapModule,
    BrowserAnimationsModule,
    PessoaModule,
    SharedModule,
    ReactiveFormsModule,
    TextMaskModule,
    ModalModule.forRoot(),
    ToastrModule.forRoot(
      // Configuracoes globais do Toast
      {
        timeOut: 5000,
        progressBar: true,
        closeButton: true
      }
    )
  ],
  providers: [
    AuthService,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
