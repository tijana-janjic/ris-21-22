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

  private url: string = "http://localhost:9000/travelagency/travel"

  constructor(
    private http: HttpClient
  ) { }

  getAllBlogArticles() : Observable<Article[]> {
    return this.http.get<Article[]>(
      this.url + '/blog'
    )
  }

  saveBlog(model: NewTour) {
    return this.http.post<Tour>(this.url + '/create-article', model)
  }

}
