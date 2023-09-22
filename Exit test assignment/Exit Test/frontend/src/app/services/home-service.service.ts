import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Stats } from '../model/Stats';
import { Observable, observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HomeServiceService {

  readonly url="http://localhost:8084";

  constructor(private http:HttpClient) { }

  public getStatsfromServer(): Observable<Stats>{
    return this.http.get<Stats>(this.url+'/getStats');
  }
}
