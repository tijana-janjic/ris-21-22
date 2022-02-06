import {Component, OnDestroy, OnInit} from '@angular/core';
import {TourService} from "../services/tour.service";
import {Country} from "../model/country";
import {Subscription} from "rxjs";
import {City} from "../model/city";
import {Landmark} from "../model/landmark";
import {Hotel} from "../model/hotel";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {HomeComponent} from "../home/home.component";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-create-article',
  templateUrl: './create-article.component.html',
  styleUrls: ['./create-article.component.css']
})
export class CreateArticleComponent implements OnInit, OnDestroy {

  title: string = ''
  countryId: number = -1
  cityId: number = -1

  countries: Country[] = []
  cities: City[] = []

  coverImage: { altText: string; data: any } | null = null;
  files: File[] = [];
  file: File | null = null;
  text: string = '';


  constructor(
    private authService: AuthService,
    private tourService: TourService,
    private router: Router,
    private snackbar: MatSnackBar) { }

  ngOnInit(): void {
    if (!this.authService.isAgentUser()){
      this.router.navigate(['/home'])
      this.snackbar.open("You dont have permission for that!","ok", {
        duration: 3000,
        horizontalPosition: 'end',
        verticalPosition: 'top',
        panelClass: ['snackbar'],
      })
    }
    this.subscriptions.push(this.tourService.getCountries().subscribe(
      (value: Country[]) => {
        this.countries = value
      }
    ))
  }

  subscriptions : Subscription[] = [];

  ngOnDestroy(): void {
    console.log('gasim se...')
    const u = this.subscriptions.length
    let i = 0
    for (const x of this.subscriptions) {
      if (!x.closed)
        i = i + 1
      x.unsubscribe();
    }
    console.log('ukupno...' + u + ' ugasio sam '+ i)
  }


  getCitiesByCountryId(countryId: number) {
    this.subscriptions.push(this.tourService.getCitiesOfCountry(countryId).subscribe(
      (value: City[]) => {
        this.cities = value
      }
    ))
  }

  fileChange(event: any) {
    this.files = event.target.files;
    if (this.files && this.files[0]) {
      const numberOfFiles = this.files.length;
      for (let i = 0; i < numberOfFiles; i++) {
        const reader = new FileReader();
        reader.onload = (e: any) => {
          this.file = e.target.result
        };
        reader.readAsDataURL(this.files[i]);
      }
    }
  }

  upload() {
    this.coverImage = {
      altText: "alt text",
      data: this.file!.slice(22)
    }
  }

  submit() {
    this.subscriptions.push(this.tourService.saveArticle(
      {
        title: this.title,
        cityId: this.cityId,
        text: this.text,
        coverImage: this.coverImage
      }
    ).subscribe(
      (value) => {
        console.log(value)
        this.router.navigate(['/home'])
        this.snackbar.open("Successful!","ok", {
          duration: 3000,
          horizontalPosition: 'end',
          verticalPosition: 'top',
          panelClass: ['snackbar'],
        })
      }
    ))
  }


}
