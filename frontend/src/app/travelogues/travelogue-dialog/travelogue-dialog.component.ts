import {Component, Inject, OnInit} from '@angular/core';
import {TourService} from "../../services/tour.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DomSanitizer} from "@angular/platform-browser";
import {Tour} from "../../model/tour";
import {CityTour} from "../../model/city-tour";
import {Travelogue} from "../../model/travelogue";

export interface DialogData {
  travelogue: Travelogue;
}

@Component({
  selector: 'app-travelogue-dialog',
  templateUrl: './travelogue-dialog.component.html',
  styleUrls: ['./travelogue-dialog.component.css']
})
export class TravelogueDialogComponent implements OnInit {

  travelogue!: Travelogue

  constructor(
    public dialogRef: MatDialogRef<TravelogueDialogComponent>,
    private readonly sanitizer: DomSanitizer,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
  ) {
    this.travelogue = data.travelogue
    console.log("otvoren: " + this.travelogue.id)
  }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }


}
