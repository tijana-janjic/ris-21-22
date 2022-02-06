import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {Country} from "../model/country";
import {City} from "../model/city";
import {Landmark} from "../model/landmark";
import {Subscription} from "rxjs";
import {TourService} from "../services/tour.service";
import {Hotel} from "../model/hotel";
import {MAT_SNACK_BAR_DATA, MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {HomeComponent} from "../home/home.component";
import {AuthService} from "../services/auth.service";


export interface NewCityTour {
  name: string,
  cityId : number
  hotelId : number
  landmarkIds: number[]
  coverImage: {
    altText : string,
    data : File | null
  } | null
}

@Component({
  selector: 'app-create-city-tour',
  templateUrl: './create-city-tour.component.html',
  styleUrls: ['./create-city-tour.component.css']
})
export class CreateCityTourComponent implements OnInit, OnDestroy {

  name: string = ''
  countryId: number = -1
  cityId: number = -1
  hotelId: number = -1;

  countries: Country[] = []
  cities: City[] = []
  landmarks: Landmark[] = [];
  hotels: Hotel[] = [];
  landmarksMap = new Map<Landmark, boolean>()
  private coverImage: { altText: string; data: any } | null = null;
  private files: File[] = [];
  private file: File | null = null;

  constructor(
              private authService: AuthService,
              private tourService: TourService,
              private router: Router,
              public snackbar: MatSnackBar) { }

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

  getLandmarks(cityId: number) {
    this.subscriptions.push(this.tourService.getLandmarks(cityId).subscribe(
        (value: Landmark[]) => {
        this.landmarks = value
        value.forEach(x => {
          this.landmarksMap.set(x, false)
        })
      }
    ))
  }

  getHotels(cityId: number) {
    this.subscriptions.push(this.tourService.getHotels(cityId).subscribe(
      (value: Hotel[]) => {
        this.hotels = value
      }
    ))
  }

  setLandmark(landmark: Landmark) {
    const checked = this.landmarksMap.get(landmark)
    this.landmarksMap.set(landmark, !checked)
  }

  getInfo(cityId: number) {
    this.getLandmarks(cityId)
    this.getHotels(cityId)
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

  getChosen() : number[] {
    let chosen : number[] = []
    this.landmarks.forEach(
      x => {
        if(this.landmarksMap.get(x))
          chosen.push(x.id)
      }
    )
    return chosen
  }

  submit() {
    this.subscriptions.push(this.tourService.saveCityTour(
      {
        name: this.name,
        cityId: this.cityId,
        hotelId: this.hotelId,
        landmarkIds: this.getChosen(),
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
