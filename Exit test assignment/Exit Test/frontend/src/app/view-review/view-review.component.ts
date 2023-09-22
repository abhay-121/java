import { Component, OnInit } from '@angular/core';
import { RegistrationService } from '../services/registration.service';
import { ProductServiceService } from '../services/product-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../model/Product';
import { Review } from '../model/Review';

@Component({
  selector: 'app-view-review',
  templateUrl: './view-review.component.html',
  styleUrls: ['./view-review.component.css']
})
export class ViewReviewComponent implements OnInit {
  code:any;
  userName:string="";
  reviewList:Array<Review>=[]
  product:Product={
    productCode:"",
    productName:"",
    productBrand:""
  }
  constructor(private productService : ProductServiceService, private service : RegistrationService, private router: Router, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.code = this.route.snapshot.paramMap.get('productCode');
    console.log(this.code);
    if(this.service.getLoginUserName()!=null){
      this.userName=this.service.getLoginUserName();
    }
    this.getReviewList(this.code);
    this.getProduct();
  }

  getProduct(){
    this.productService.getProductById(this.code).subscribe((data)=>{
      this.product = data;
    }, (error)=>{
      console.log(error);
    })
  }

  getReviewList(id:any){
    this.productService.getReviewList(id).subscribe((data) =>{
      this.reviewList=data;
      console.log(this.reviewList);
    })
  }

  logout(){
    if(confirm("You are about to logout?")){
      this.service.logout();
      this.router.navigate([""]);
    }
  }
}

