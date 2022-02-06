import {Component, Inject, OnInit} from '@angular/core';
import {TourService} from "../../services/tour.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DomSanitizer} from "@angular/platform-browser";
import {Tour} from "../../model/tour";
import {CityTour} from "../../model/city-tour";
import {Article} from "../../model/article";

export interface DialogData {
  article: Article;
}

@Component({
  selector: 'app-article-dialog',
  templateUrl: './article-dialog.component.html',
  styleUrls: ['./article-dialog.component.css']
})
export class ArticleDialogComponent implements OnInit {

  article!: Article

  constructor(
    public dialogRef: MatDialogRef<ArticleDialogComponent>,
    private readonly sanitizer: DomSanitizer,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
  ) {
    this.article = data.article
    console.log("otvoren: " + this.article.id)
  }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }


}
