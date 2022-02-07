import {Component, Inject, OnInit} from '@angular/core';
import {Tour} from "../../model/tour";
import {TourService} from "../../services/tour.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Subscription} from "rxjs";
import {Article} from "../../model/article";

interface DialogData {
  articles: Article[]
}

@Component({
  selector: 'app-agent-articles',
  templateUrl: './agent-articles.component.html',
  styleUrls: ['./agent-articles.component.css']
})
export class AgentArticlesComponent implements OnInit {

  displayedColumns = ['Title', 'City', 'Text'];
  articles: Article[];

  constructor(
    private tourService: TourService,
    public dialogRef: MatDialogRef<AgentArticlesComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
  ) {
    this.articles = data.articles
  }

  s!: Subscription

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.s?.unsubscribe()
  }

  deleteArticle(id: number) {
    this.s = this.tourService.deleteArticle(id).subscribe(
      value => {
        this.dialogRef.close()
      }
    );
  }
}
