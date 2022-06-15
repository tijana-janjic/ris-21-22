import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Article} from "../model/article";
import {HttpClient} from "@angular/common/http";
import {Tour} from "../model/tour";
import {CityTour} from "../model/city-tour";
import {NewTour} from "../create-tour/create-tour.component";

@Injectable({
  providedIn: 'root'
})
export class BlogService {

  private url: string = "http://localhost:9000/travelagency/blog"

  constructor(
    private http: HttpClient
  ) { }

  getAllBlogArticles() : Observable<Article[]> {
    return this.http.get<Article[]>(
      this.url 
    )
  }


  // blog

  saveArticle(model: any){
    return this.http.post<Article>(this.url + '/create-article', model)
  }

  deleteArticle(id: number) {
    return this.http.delete(this.url + '/delete-article?id='+id)
  }

  getArticlesByAgent() {
    return this.http.get<Article[]>(this.url + '/agent/articles')
  }

}
