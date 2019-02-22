import { Component, OnInit } from '@angular/core';
import {UserModel} from "../../models/user.model";
import {UserService} from "../../services/user.service";
import { FormControl, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {
  users: UserModel[];
  addForm: FormGroup;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getAllUsers().subscribe(
      (users: UserModel[]) => this.users = users
    );
    this.initForms();
  }

  onAddSubmit() {
    const userDetails =
      {'username':  this.addForm.value['username'],
      'email' : this.addForm.value['email']};
    this.userService.addUser(userDetails);
    this.addForm.reset();
    window.location.reload();
    alert("added user");
  }

  private initForms() {
    this.addForm = new FormGroup({
      'username': new FormControl(null, Validators.required),
      'email': new FormControl(null, [Validators.required, Validators.email]),
    });
  }

  onDeleteUser(id: number) {
    this.userService.deleteUserById(id);
    alert("delete user");
    window.location.reload();
  }

  logout() {
    this.userService.logoutUser();
  }
}
