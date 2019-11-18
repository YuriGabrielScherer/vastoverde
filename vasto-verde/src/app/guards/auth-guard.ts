import { AuthService } from './../login/auth.service';
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {

    console.log(this.authService.usuarioAutenticado());

    if (this.authService.usuarioAutenticado()) {
      console.log('pode');
      return true;
    } else {
      console.log('n pode');
      this.router.navigate(['/login']);
      return false;
    }
  }
}
