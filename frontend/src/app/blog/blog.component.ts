import {Component, OnDestroy, OnInit} from '@angular/core';
import {Article} from "../model/article";
import {CityTour} from "../model/city-tour";
import {TourDialogComponent} from "../tours/tour-dialog/tour-dialog.component";
import {ArticleDialogComponent} from "./article-dialog/article-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {Subscription} from "rxjs";
import {BlogService} from "../services/blog.service";


@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent implements OnInit, OnDestroy {
  blog: Article[] = []
  private articleMap = new Map<number, Article>()

  constructor(public articleService: BlogService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.getAllBlog()
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

  getAllBlog() {
    this.subscriptions.push(this.articleService.getAllBlogArticles().subscribe(
      (response : Article[] ) => {
        response.forEach( (x:Article) => {
          console.log(JSON.stringify(x))
          this.articleMap.set(x.id, x)
        })
        this.blog.push(...this.articleMap.values())
        console.log('unutar subscribe ' + JSON.stringify(this.articleMap))
      }
    ))
  }

  openArticle(article: Article) {
    console.log('otvaram : ' + JSON.stringify(article));
    console.log(JSON.stringify(this.articleMap));
    const dialogRef = this.dialog.open(ArticleDialogComponent, {
      width: '1000px',
      data: {
        article: article
      },
    });

    this.subscriptions.push(dialogRef.afterClosed()
      .subscribe(result => {
        console.log('The dialog was closed');
      }));
  }
}
