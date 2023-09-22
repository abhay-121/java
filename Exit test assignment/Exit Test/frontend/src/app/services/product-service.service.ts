import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Review } from '../model/Review'
import { Product } from '../model/Product';


@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {

  constructor(private http: HttpClient) { }
  readonly url="http://localhost:8084";

  public getProductBySearch(search:String):Observable<any>{
    return this.http.get<any>(this.url +"/getSearchedProduct" + "/"+search);
  }

  // Post reviews with productCode
  public postReview(reviews: Review, id:String):Observable<{}>{
    return this.http.put<{}>(this.url+'/postReview'+'/'+id, reviews);
  }

  public getProductById(code:string){
    return this.http.get<any>(this.url + '/getProductById'+'/'+code);
  }

  public getReviewList(code:string):Observable<Array<Review>>{
    return this.http.get<Array<Review>>(this.url+'/getReviews/'+code);
  }

  public askForReview(product : Product){
    return this.http.post<any>(this.url+'/askforReview/', product);
  }
}
