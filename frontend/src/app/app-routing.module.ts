import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from "./login/login.component";
import { RegistrationComponent } from "./registration/registration.component";
import {CreateTourComponent} from "./create-tour/create-tour.component";
import {ToursComponent} from "./tours/tours.component";
import {BlogComponent} from "./blog/blog.component";
import {CreateArticleComponent} from "./create-article/create-article.component";
import {CreateCityTourComponent} from "./create-city-tour/create-city-tour.component";
import {AccountComponent} from "./account/account.component";

const routes: Routes = [
  { path: 'tours', component: ToursComponent },
  { path: 'account', component: AccountComponent },
  { path: 'tours/:id', component: ToursComponent },
  { path: 'blog', component: BlogComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'create-tour', component: CreateTourComponent },
  { path: 'create-article', component: CreateArticleComponent },
  { path: 'create-city-tour', component: CreateCityTourComponent },
  { path: '**', component: HomeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
