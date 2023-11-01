import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Observable} from "rxjs";
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class AdminGuardService implements CanActivate{

  constructor( private router : Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    const token : string = localStorage.getItem('token') as string;
    if(token){
      const jwtHelper = new JwtHelperService();
      const decodedJwt = jwtHelper.decodeToken(token);
      if(decodedJwt.authorities[0].authority !== 'ROLE_ADMIN'){
        this.router.navigate(['login']);
        return false;
      }
      return true;
    }
    return false;
  }
}
