import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductServiceService } from '../services/product-service.service';
import { RegistrationService } from '../services/registration.service';
import { Review } from '../model/Review';
import { Product } from '../model/Product';

@Component({
  selector: 'app-post-reviews',
  templateUrl: './post-reviews.component.html',
  styleUrls: ['./post-reviews.component.css']
})
export class PostReviewsComponent implements OnInit {

  product:Product={
    productCode:"",
    productName:"",
    productBrand:""
  }

  reviews: Review ={
    id:"",
    rating:"",
    message:"",
    heading:"",
    date: "",
    userName:"",
    approved:false
  }

  constructor(private productService : ProductServiceService, private service : RegistrationService, private router: Router, private route:ActivatedRoute) { }
  code:any
  ngOnInit(): void {
    this.code = this.route.snapshot.paramMap.get('productCode');
    if(this.service.getLoginUserName()!=null){
      this.reviews.userName=this.service.getLoginUserName();
    }
    this.getProduct();
  }

  getProduct(){
    this.productService.getProductById(this.code).subscribe((data)=>{
      this.product = data;
    }, (error)=>{
      console.log(error);
    })
  }

  postReviwesData(){
    // console.log(this.reviews);
    this.productService.postReview(this.reviews, this.code).subscribe((data) => {
      alert("Post submitted Successfully");
      this.router.navigate(["/result/"+this.code])
    })
  }

  logout(){
    if(confirm("You are about to logout?")){
      this.service.logout();
      this.router.navigate([""]);
    }
  }

}
