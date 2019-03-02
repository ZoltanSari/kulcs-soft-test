import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { LoginComponent } from './components/login/login.component';
import {AppRoutingModule} from "./app-routing-module";
import { AdminPageComponent } from './components/admin-page/admin-page.component';
import {ReactiveFormsModule} from "@angular/forms";
import { RegistrationComponent } from './components/registration/registration.component';
import {UserService} from "./services/user.service";
import {AuthInterceptor} from "./interceptors/auth-interceptor";
import {AuthService} from "./services/auth.service";
import {Guard} from "./guard/guard";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminPageComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [AuthService, UserService, {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}, Guard],

  bootstrap: [AppComponent]
})
export class AppModule { }
