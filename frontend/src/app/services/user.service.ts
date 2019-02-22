import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {UserModel} from "../models/user.model";
import {Router} from "@angular/router";
import {AuthService} from "./auth.service";
import {AdminModel} from "../models/Admin.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl = 'http://localhost:8080/admin';

  constructor(private httpClient: HttpClient,
              private router: Router,
              private authService: AuthService) {
  }

  getAllUsers() {
    return this.httpClient.get<UserModel[]>(`${this.baseUrl}/users`)
  }

  deleteUserById(id: number) {
    this.httpClient.delete<void>(`${this.baseUrl}/users/${id}`).subscribe();
  }

  addUser(userDTO: {
    username: string,
    email: string
  }) {
    return this.httpClient.post<any>(`${this.baseUrl}/users/add-user`, userDTO).subscribe();
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
      },)
  }

  register(adminDTO: {
    username: string,
    password: string
  }) {
    this.httpClient.post<any>(`${this.baseUrl}/registration`, adminDTO).subscribe();
    this.router.navigate(['login']);
  }
}

