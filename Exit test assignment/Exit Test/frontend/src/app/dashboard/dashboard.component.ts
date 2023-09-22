import { Component, OnInit } from '@angular/core';
import { RegistrationService } from '../services/registration.service';
import { HomeServiceService } from '../services/home-service.service';
import { Router } from '@angular/router';
import { Product } from '../model/Product';
import { Stats } from '../model/Stats';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  product: Array<Product> = [];
  stats: Stats={
    totalUsers:0,
    totalProducts:0,
    totalReviews:0
  }

  constructor( private router:Router, private service: RegistrationService, private homeService: HomeServiceService) { }
  userName:string ="";
  ngOnInit(): void {
    if(this.service.getLoginUserName() != null){
      this.userName=this.service.getLoginUserName();
    }
    this.getStats()
  }

  logout(){
    if(confirm("you are about to logout ?")){
      this.service.logout();
      this.router.navigate([""])
    }
  }

  searchProduct(searchValue:any){
    // console.log("search value " +searchValue);
    this.router.navigate(["result"+"/"+searchValue]);
  }

  getStats(){
    this.homeService.getStatsfromServer().subscribe((res) => {
      this.stats.totalUsers=res.totalUsers,
      this.stats.totalProducts=res.totalProducts,
      this.stats.totalReviews=res.totalReviews;
    },
    (error) => {
      console.log(error);
    }
    )
  }

}
