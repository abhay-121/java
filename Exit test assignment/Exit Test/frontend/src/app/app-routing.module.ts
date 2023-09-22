import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent} from './login/login.component';
import { RegisterComponent} from './register/register.component';
import { DashboardComponent} from './dashboard/dashboard.component';
import { ResultComponent} from './result/result.component';
import { ViewReviewComponent } from './view-review/view-review.component';
import { PostReviewsComponent } from './post-reviews/post-reviews.component';
import { HomeComponent } from './home/home.component';
import { AuthGuardGuard } from './guards/auth-guard.guard';
import { AskForReviewComponent } from './ask-for-review/ask-for-review.component';

const routes: Routes = [
  { path:"login", component:LoginComponent},
  { path:"register", component:RegisterComponent},
  { path:"dashboard", component:DashboardComponent, canActivate:[AuthGuardGuard]},
  { path:"result/:productSearch", component:ResultComponent,canActivate:[AuthGuardGuard]},
  { path:"review/:productCode", component:ViewReviewComponent,canActivate:[AuthGuardGuard]},
  { path:"postReview/:productCode", component:PostReviewsComponent,canActivate:[AuthGuardGuard]},
  { path:"", component:HomeComponent},
  { path:"askforReview", component:AskForReviewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
