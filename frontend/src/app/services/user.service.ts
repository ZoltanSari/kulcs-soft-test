import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {UserModel} from "../models/user.model";
import {Router} from "@angular/router";
import {AuthService} from "./auth.service";
import {AdminModel} from "../models/Admin.model";
import {of} from "rxjs";
import {catchError, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl = 'https://kulcs-soft-assignment.herokuapp.com';

  /**
   * If you would like run the app as local, use this baseUrl:
   * baseUrl = 'http://localhost:8080'
   **/

  // baseUrl = 'http://localhost:8080';


  constructor(private httpClient: HttpClient,
              private router: Router,
              private authService: AuthService) {
  }

  getAllUsers() {
    return this.httpClient.get<UserModel[]>(`${this.baseUrl}/admin/users`)
  }

  deleteUserById(id: number) {
    this.httpClient.delete<void>(`${this.baseUrl}/admin/users/${id}`).subscribe();
  }

  addUser(userDTO: {
    username: string,
    email: string
  }) {
    return this.httpClient.post<any>(`${this.baseUrl}/admin/users/add-user`, userDTO).subscribe();
  }

  login(username: string, password: string) {
    return this.httpClient.post<AdminModel>(`${this.baseUrl}/login`,
      {username: username, password: password},
      {observe: 'response'}).subscribe(
      (response: HttpResponse<any>) => {
        if (response.headers.get('Authorization')) {
          this.authService.saveToken(response.headers.get('Authorization'));
          this.router.navigate(['admin-page']);
        }
      },
      (response: HttpErrorResponse) => this.handleError(response));
  }

  register(adminDTO: {
    username: string,
    password: string
  }) {
    this.httpClient.post<any>(`${this.baseUrl}/registration`, adminDTO)
      .pipe(
        tap(() => console.log('User join')),
        catchError(response => this.handleError(response)))
      .subscribe();
    if (this.authService.isLoggedIn) {
      this.router.navigate(['login']);
    }
  }

  logoutUser() {
    this.authService.logout();
  }

  private handleError<T> (error: HttpErrorResponse, result?: T) {
    console.error(error);
    console.error(error.error['error']);
    console.error(error.error['message']);
    alert(error.error['message']);
    if (error.status === 401) {
      sessionStorage.removeItem('token');
      this.router.navigate(['/']);
    }
    return of(result as T);
  }
}

