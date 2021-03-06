import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registerForm: FormGroup;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.initForm()
  }

  onRegister() {
    const adminDetails = {
      'username': this.registerForm.value['registerName'],
      'password': this.registerForm.value['registerPassword'],
    };
    this.userService.register(adminDetails);
  }

  initForm() {
    this.registerForm = new FormGroup({
      'registerName': new FormControl(null, Validators.required),
      'registerPassword': new FormControl(null, Validators.required),
    })
  }

}
