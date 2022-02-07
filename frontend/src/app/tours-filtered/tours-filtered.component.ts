import {Component, OnDestroy, OnInit} from '@angular/core';
import {TourService} from "../services/tour.service";
import {Tour} from "../model/tour";
import {MatDialog} from "@angular/material/dialog";
import {CityTour} from "../model/city-tour";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";
import {TourDialogComponent} from "../tours/tour-dialog/tour-dialog.component";

@Component({
  selector: 'app-tours-filtered',
  templateUrl: './tours-filtered.component.html',
  styleUrls: ['./tours-filtered.component.css']
})
export class ToursFilteredComponent implements OnInit, OnDestroy {


  tourMap: Map<number,Tour> = new Map<number, Tour>()
  cityTourMap: Map<number,CityTour> = new Map<number, CityTour>()
  tours: Tour[] = []

  constructor(private tourService : TourService, public dialog: MatDialog, public router: Router) { }

  ngOnInit(): void {
    console.log('Heloooo' + this.router.url.split('/')[2])
    this.getAllTours()
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

  getAllTours() {
    this.subscriptions.push(this.tourService.getAllTours().subscribe(
      (response : Tour[] ) => {
        response.forEach( (x:Tour) => {
          this.tourMap.set(x.id, x)
        })
        this.tours.push(...this.tourMap.values())
        console.log('unutar subscribe ' + JSON.stringify(this.tourMap))
      }
    ))
  }

  openTour(tour: Tour) {
    this.subscriptions.push(this.tourService.getCityToursOfTour(tour.id).subscribe(
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
        this.subscriptions.push(dialogRef.afterClosed().subscribe(result => {
          console.log('The dialog was closed');
        }));
      }
    ))
  }

}

