import { Injectable } from '@angular/core';

import { Subject } from 'rxjs';
import {Router} from "@angular/router";

@Injectable()
export class AuthService {

  isLoggedIn = new Subject<boolean>();

  constructor(private router: Router) {
  }

  saveToken(token: string) {
    this.isLoggedIn.next(true);
    sessionStorage.setItem('token', token.substring(7));
  }

  logout() {
    sessionStorage.removeItem('token');
    this.isLoggedIn.next(false);
    this.router.navigate(['login']);

  }
}

