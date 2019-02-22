import {CanActivate, Router} from "@angular/router";
import {Injectable} from "@angular/core";

@Injectable()
export class Guard implements CanActivate{

  constructor(private router: Router) {
      }

  canActivate() {
    if (!sessionStorage.getItem('token')) {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }

}
