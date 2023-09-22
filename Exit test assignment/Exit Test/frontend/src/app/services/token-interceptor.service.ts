import { Injectable, Injector } from '@angular/core';
import { HttpInterceptor } from '@angular/common/http';
import { RegistrationService } from './registration.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  constructor( private injectot: Injector, private service:RegistrationService) { }

  intercept(req:any, next: any){
    const token=sessionStorage.getItem("token");

    if(token){
      const cloned = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${token}`)
      });
      return next.handle(cloned);
    }
    else{
      return next.handle(req);
    }
  }
}
