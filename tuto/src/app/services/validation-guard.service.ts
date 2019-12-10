import { Injectable } from '@angular/core';
import { Router, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class ValidationGuardService {

  constructor(private _router: Router, private authService: AuthenticationService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    
    if(this.authService.isUserLoggedIn() && sessionStorage.getItem('userRole') === 'ROLE_DT')
       return true;

    return false;
  }
}
