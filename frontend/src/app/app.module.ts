import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from "@angular/router";
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import { MatSidenavModule } from "@angular/material/sidenav";
import { MatListModule } from "@angular/material/list";
import { MatMenuModule } from "@angular/material/menu";
import { MatTreeModule } from "@angular/material/tree";
import { MatToolbarModule } from '@angular/material/toolbar';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';

import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { RegistrationComponent } from './registration/registration.component';
import { MatDatepickerModule } from "@angular/material/datepicker";
import { MatNativeDateModule } from '@angular/material/core';
import { FormsModule } from "@angular/forms";
import { MatSelectModule } from "@angular/material/select";
import { CreateTourComponent } from './create-tour/create-tour.component';
import { MatStepperModule } from "@angular/material/stepper";
import { ToursComponent } from './tours/tours.component';
import { MatCardModule } from "@angular/material/card";
import { TourDialogComponent } from './tours/tour-dialog/tour-dialog.component';
import { MatDialogModule } from "@angular/material/dialog";
import { MatExpansionModule } from "@angular/material/expansion";
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { HttpRequestInterceptor } from "./auth/interceptor/HttpRequestInterceptor";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { TraveloguesComponent } from './travelogues/travelogues.component';
import { AccountComponent } from './account/account.component';
import { TravelogueDialogComponent } from './travelogues/travelogue-dialog/travelogue-dialog.component';
import {MatCheckboxModule} from "@angular/material/checkbox";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    RegistrationComponent,
    CreateTourComponent,
    ToursComponent,
    TourDialogComponent,
    TraveloguesComponent,
    AccountComponent,
    TravelogueDialogComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatSidenavModule,
    MatListModule,
    RouterModule,
    MatMenuModule,
    MatTreeModule,
    AppRoutingModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    FormsModule,
    HttpClientModule,
    MatSelectModule,
    MatStepperModule,
    MatCardModule,
    MatDialogModule,
    MatExpansionModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    MatCheckboxModule
  ],
  providers: [
     { provide: HTTP_INTERCEPTORS, useClass: HttpRequestInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
