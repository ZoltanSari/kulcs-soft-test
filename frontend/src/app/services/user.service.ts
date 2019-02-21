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

  deleteUserById(id: number) {
    this.httpClient.delete<void>(`${this.baseUrl}/${id}`).subscribe();
  }

  addUser(userDTO: { username: string,
                      email: string} ) {
    return this.httpClient.post<any>(`${this.baseUrl}/add-user`, userDTO).subscribe();
  }
}
