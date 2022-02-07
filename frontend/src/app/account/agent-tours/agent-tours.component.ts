import {Component, Inject, OnInit} from '@angular/core';
import {Tour} from "../../model/tour";
import {AuthService} from "../../services/auth.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Subscription} from "rxjs";
import {TourService} from "../../services/tour.service";

interface DialogData {
  tours: Tour[]
}

@Component({
  selector: 'app-agent-tours',
  templateUrl: './agent-tours.component.html',
  styleUrls: ['./agent-tours.component.css']
})
export class AgentToursComponent implements OnInit {

  displayedColumns = ['Name', 'Starting date', 'End date', 'Deadline', 'Price', 'Actions'];
  tours: Tour[];

  constructor(
    private tourService: TourService,
    public dialogRef: MatDialogRef<AgentToursComponent>,
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

  deleteTour(id: number) {
    this.s = this.tourService.deleteTour(id).subscribe(
      value => {
        this.dialogRef.close()
      }
    );
  }


}
