import { Component, OnInit } from '@angular/core';
import {TourService} from "../services/tour.service";
import {Tour} from "../model/tour";
import {MatDialog} from "@angular/material/dialog";
import {CityTourDialogComponent} from "../city-tour-dialog/city-tour-dialog.component";
import {TourDialogComponent} from "../tour-dialog/tour-dialog.component";
import {CityTour} from "../model/city-tour";

@Component({
  selector: 'app-tours',
  templateUrl: './tours.component.html',
  styleUrls: ['./tours.component.css']
})
export class ToursComponent implements OnInit {

  tourMap: Map<number,Tour> = new Map<number, Tour>()
  cityTourMap: Map<number,CityTour> = new Map<number, CityTour>()
  tours: Tour[] = []

  constructor(private tourService : TourService, public dialog: MatDialog) { }

  ngOnInit(): void {
    console.log('Heloooo')
    this.getAllTours()
  }

  getAllTours() {
      this.tourService.getAllTours().subscribe(
        (response : Tour[] ) => {
          response.forEach( (x:Tour) => {
            this.tourMap.set(x.id, x)
          })
          this.tours.push(...this.tourMap.values())
          console.log('unutar subscribe ' + JSON.stringify(this.tourMap))
        }
      )
  }

  openTour(tour: Tour) {
    this.tourService.getCityToursFor(tour.id).subscribe(
      (response : CityTour[] ) => {
        this.cityTourMap.clear()
        response.forEach( (x:CityTour) => {
          this.cityTourMap.set(x.id, x)
        })
        console.log('tourMap.values() ' + JSON.stringify(this.tourMap.values()))

        console.log('otvaram : ' + tour.id);
        const dialogRef = this.dialog.open(TourDialogComponent, {
          width: '1000px',
          data: {
            tour: tour,
            cityTourMap: this.cityTourMap
          },
        });

        dialogRef.afterClosed().subscribe(result => {
          console.log('The dialog was closed');
        });
      }
    )


  }

}
