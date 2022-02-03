import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {CityTour} from "../model/city-tour";
import {Tour} from "../model/tour";
import {DomSanitizer} from "@angular/platform-browser";
import {TourService} from "../services/tour.service";

export interface DialogData {
  tour: Tour;
  cityTourMap: Map<number,CityTour>
}

@Component({
  selector: 'app-tour-dialog',
  templateUrl: './tour-dialog.component.html',
  styleUrls: ['./tour-dialog.component.css']
})
export class TourDialogComponent implements OnInit {

  tour!: Tour
  gallery = new Array<string| ArrayBuffer>()
  cityTourMap : Map<number,CityTour> = new Map<number,CityTour>()
  cityTours : CityTour[] = []

  constructor(
    private tourService : TourService,
    public dialogRef: MatDialogRef<TourDialogComponent>,
    private readonly sanitizer: DomSanitizer,
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

  onNoClick(): void {
    this.dialogRef.close();
  }

}
