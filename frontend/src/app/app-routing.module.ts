import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from "./login/login.component";
import { RegistrationComponent } from "./registration/registration.component";
import {CreateTourComponent} from "./create-tour/create-tour.component";
import {ToursComponent} from "./tours/tours.component";
import {TraveloguesComponent} from "./travelogues/travelogues.component";

const routes: Routes = [
  { path: 'tours', component: ToursComponent },
  { path: 'tours/:id', component: ToursComponent },
  { path: 'travelogues', component: TraveloguesComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'create-tour', component: CreateTourComponent },
  { path: '**', component: HomeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
