import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { HardcodedAuthenticationService } from '../service/harcoded-authentication.service';
// import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    private hardcodedAuthenticationService: HardcodedAuthenticationService
  ) {}
  canActivate() {
    if (!this.hardcodedAuthenticationService.loggedinSession()) {
      this.router.navigate(['accounts']);
    }
    return this.hardcodedAuthenticationService.loggedinSession();
  }
}
