import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserModel} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl = 'http://localhost:8080/users';

  constructor(private httpClient: HttpClient) { }

  getAllUsers() {
    return this.httpClient.get<UserModel[]>(this.baseUrl)
  }
}
