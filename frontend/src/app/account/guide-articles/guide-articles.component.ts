import {Component, Inject, OnInit} from '@angular/core';
import {Tour} from "../../model/tour";
import {TourService} from "../../services/tour.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Subscription} from "rxjs";

interface DialogData {
  tours: Tour[]
}

@Component({
  selector: 'app-guide-articles',
  templateUrl: './guide-articles.component.html',
  styleUrls: ['./guide-articles.component.css']
})
export class GuideArticlesComponent implements OnInit {

  displayedColumns = ['Name', 'Starting date', 'End date', 'Deadline', 'Price'];
  tours: Tour[];

  constructor(
    private tourService: TourService,
    public dialogRef: MatDialogRef<GuideArticlesComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
  ) {
    this.tours = data.tours
  }

  s!: Subscription

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.s?.unsubscribe()
  }

}
