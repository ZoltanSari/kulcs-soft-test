import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {AdminPageComponent} from "./components/admin-page/admin-page.component";
import {RegistrationComponent} from "./components/registration/registration.component";
import {Guard} from "./guard/guard";

const routes: Routes = [
  { path: '', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  { path: 'admin-page', component: AdminPageComponent, canActivate: [Guard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
