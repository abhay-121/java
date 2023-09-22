import { Component, OnInit } from '@angular/core';
import { Product } from '../model/Product';
import { RegistrationService } from '../services/registration.service';
import { Router } from '@angular/router';
import { ProductServiceService } from '../services/product-service.service';

@Component({
  selector: 'app-ask-for-review',
  templateUrl: './ask-for-review.component.html',
  styleUrls: ['./ask-for-review.component.css']
})
export class AskForReviewComponent implements OnInit {

  product: Product={
    productCode:"",
    productBrand:"",
    productName:""
  }
  userName:string="";
  message="";
  constructor(private service:RegistrationService, private router: Router, private proService:ProductServiceService) { }

  ngOnInit(): void {
    if(this.service.getLoginUserName() != null){
      this.userName=this.service.getLoginUserName();
    }
  }

  askForReview(){
    console.log(this.product);
    this.proService.askForReview(this.product).subscribe(data =>{
      this.message="Ohh! No product present for review, redirecting to dashboard";
      setTimeout(() => {
        setTimeout(() => {
          this.router.navigate(["/dashboard"]);
        });
      }, 5000);
    },
    error =>{
      this.message="Product already Exit with this productCode, redirecting to review page..";
      setTimeout(() => {
        setTimeout(() => {
          this.router.navigate(["review", this.product.productCode]);
        });
      }, 10000);
    }

    )
  }

  logout(){
    if(confirm("you are about to logout ?")){
      this.service.logout();
      this.router.navigate([""])
    }
  }

}
