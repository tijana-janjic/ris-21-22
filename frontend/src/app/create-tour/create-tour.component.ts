import {Component, OnDestroy, OnInit} from '@angular/core';
import {Account} from "../model/account";
import {DateFilterFn} from "@angular/material/datepicker";
import {CityTour} from "../model/city-tour";
import {TourService} from "../services/tour.service";
import {AuthService} from "../services/auth.service";
import {FileDescription} from "../model/file";
import {Subscription} from "rxjs";

export interface NewTour {
  name : string
  startDate : string
  endDate : string
  deadline : string
  price : number
  maxPassengers : number
  transportationType : string
  tourType : string
  guideEmail : number
  description: string
  coverImage: {
    altText: string,
    data: Blob | null
  }
}

@Component({
  selector: 'app-create-tour',
  templateUrl: './create-tour.component.html',
  styleUrls: ['./create-tour.component.css']
})
export class CreateTourComponent implements OnInit, OnDestroy {

  model: NewTour = {
    name : '',
    startDate : '',
    endDate : '',
    deadline : '',
    price : 0,
    maxPassengers : 50,
    transportationType : '',
    tourType : '',
    guideEmail : -1,
    description : '',
    // cityTours : [],
    coverImage: {
      altText : 'alt-text',
      data : null
    }
  }
  cityTours : CityTour[] = []
  cityTourMapChosen = new Map<CityTour, boolean>()

  guides : Account[] = []
  guide!: Account

  file: File | null = null
  files: File[] = []

  constructor(private tourService : TourService,
              private authService: AuthService) { }

  ngOnInit(): void {
    this.subscriptions.push(this.tourService.getCityTours().subscribe(
      value => {
        this.cityTours = value
        value.forEach(x => {
          this.cityTourMapChosen.set(x, false)
        })
      }
    ))
    this.subscriptions.push(this.authService.getAllGuides().subscribe(
      value => {
        this.guides = value
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

  getMinDate() {
    return new Date();
  }

  filterOlder: DateFilterFn<Date | null> = (date: Date | null) => {
    return date != null && date >= this.getMinDate();
  };

  setCityTour(tour: CityTour) {
    const checked = this.cityTourMapChosen.get(tour)
    this.cityTourMapChosen.set(tour, !checked)
  }

  getChecked(): number[] {
    let checked: number[] = [];
    for (const cityTour of this.cityTourMapChosen.keys()) {
      if (this.cityTourMapChosen.get(cityTour))
        checked.push(cityTour.id)
    }
    return checked
  }

  submit(){
    const checked = this.getChecked()

    this.subscriptions.push(this.tourService.saveTour(this.model, checked ).subscribe(
      value => {
        console.log(JSON.stringify(value))
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
    this.file = this.files[0]
  }

  upload() {
    this.model.coverImage = {
      altText: "alt text",
      data: this.file!.slice(22)
    }
  }

}
