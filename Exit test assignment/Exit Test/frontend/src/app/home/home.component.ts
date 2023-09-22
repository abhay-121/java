import { Component, OnInit } from '@angular/core';
import { HomeServiceService } from '../services/home-service.service';
import { Stats } from '../model/Stats';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  stats: Stats={
    totalUsers:0,
    totalProducts:0,
    totalReviews:0
  }

  constructor(private service : HomeServiceService) { }

  ngOnInit(): void {
    this.getStats();
  }

  getStats(){
    this.service.getStatsfromServer().subscribe((res) => {
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
