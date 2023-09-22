import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from '../services/product-service.service';
import { Product } from '../model/Product';
import { Router, ActivatedRoute } from '@angular/router';
import { RegistrationService } from '../services/registration.service';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {
  filterString:string='';
  product:Array<Product>=[];
  userName:string="";
  constructor(private service:ProductServiceService, private registrationService: RegistrationService, 
    private aRouter:ActivatedRoute, private router:Router) { }

  ngOnInit(): void {
    if(this.registrationService.getLoginUserName()!=null){
      this.userName=this.registrationService.getLoginUserName()
    }
    this.resultList();

  }

  resultList(){
    const code:any=this.aRouter.snapshot.paramMap.get('productSearch');
    this.service.getProductBySearch(code).subscribe((data)=>{
      this.product=data;
      // console.log(this.product);
      if(data.length==0){
        this.notAvailable();
      }
    },(error)=>{
      console.log(error);
    }
    )
  }

  notAvailable(){
    if(this.product.length==0){
      return true;
    }
    return false;
  }

  logout(){
    if(confirm("you are about to logout ?")){
      this.registrationService.logout();
      this.router.navigate([""])
    }
  }

}
