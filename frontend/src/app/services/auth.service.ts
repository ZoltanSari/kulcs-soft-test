import { Injectable } from '@angular/core';

import { Subject } from 'rxjs';

@Injectable()
export class AuthService {

  isLoggedIn = new Subject<boolean>();

  constructor() {}

  saveToken(token: string) {
    this.isLoggedIn.next(true);
    sessionStorage.setItem('token', token.substring(7));
  }
}
