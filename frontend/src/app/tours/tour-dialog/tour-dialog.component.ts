import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {CityTour} from "../../model/city-tour";
import {Tour} from "../../model/tour";
import {DomSanitizer} from "@angular/platform-browser";
import {TourService} from "../../services/tour.service";
import {Subscription} from "rxjs";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";

export interface DialogData {
  tour: Tour;
  cityTourMap: Map<number,CityTour>
}

@Component({
  selector: 'app-tour-dialog',
  templateUrl: './tour-dialog.component.html',
  styleUrls: ['./tour-dialog.component.css']
})
export class TourDialogComponent implements OnInit, OnDestroy {

  tour!: Tour
  gallery = new Array<string| ArrayBuffer>()
  cityTourMap : Map<number,CityTour> = new Map<number,CityTour>()
  cityTours : CityTour[] = []

  constructor(
    private tourService : TourService,
    public authService : AuthService,
    public dialogRef: MatDialogRef<TourDialogComponent>,
    public snackbar: MatSnackBar,
    private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
  ) {
    this.tour = data.tour
    this.cityTourMap = data.cityTourMap
    this.cityTours.push(...data.cityTourMap.values())
    console.log("cityTourMap: " + JSON.stringify(this.cityTours))
    console.log("cityTourMap: " + JSON.stringify(data.cityTourMap))
    console.log("otvoren: " + this.tour.id)
  }

  ngOnInit(): void {
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

  onNoClick(): void {
    this.dialogRef.close();
  }

  reserve(){
    this.subscriptions.push(this.authService.reserve(this.tour.id).subscribe(
      response => {
        this.dialogRef.close()
        this.router.navigate(['/home'])
        this.snackbar.open("Reservation successfully added!","ok", {
          duration: 3000,
          horizontalPosition: 'end',
          verticalPosition: 'top',
          panelClass: ['green-snackbar'],
        })
      },
      error => {
        this.snackbar.open("Reservation unsuccessful!","ok", {
          duration: 3000,
          horizontalPosition: 'end',
          verticalPosition: 'top',
          panelClass: ['red-snackbar'],
        })
      }
    ))
  }

}
