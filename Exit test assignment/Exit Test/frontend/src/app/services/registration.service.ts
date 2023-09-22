import { Injectable } from '@angular/core';
import { User} from '../model/User';
import { Observable} from 'rxjs';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http:HttpClient) { }
  readonly url="http://localhost:8084";

 // This method is used for User Registration
  public registerUserFromRemote(user:User):Observable<any>{
    return this.http.post(this.url+'/registerUser', user);
  }

  // This method is used for Authentication
  public loginUser(user:any):Observable<any>{
    return this.http.post(this.url+'/authUser', user);
  }
  // Store token and userName in sessionStorage
  saveUser(token: string, userName:string){
    sessionStorage.setItem("token", token);
    sessionStorage.setItem("userName", userName)
  }

  isloggedIn(){
    let token = sessionStorage.getItem("token");
    if(token == undefined || token == '' || token == null){
      return false;
    }
    else{
      return true;
    }
  }
  //Logout User
  logout(){
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("userName");
  }

  //Get LoggedIn UserName
  getLoginUserName(){
    let userName = sessionStorage.getItem('userName');
    if(userName == undefined || userName == '' || userName == null){
      return "No user found, Please login";
    }
    return userName;
  }
}