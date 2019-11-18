import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

   email = '';
   senha = '';

  constructor(
    private authService: AuthService
  ) { }

  ngOnInit() {
  }

  // Metodo que realiza o Login
  realizarLogin() {
    this.authService.realizarLogin(this.email, this.senha);
  }

}
