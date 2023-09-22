import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { RegistrationService} from '../services/registration.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user = {
    email:"",
    password:""
  }
  message="";
  constructor( private router:Router, private service: RegistrationService) { }

  ngOnInit(): void {
  }


  doLogin(){
    this.service.loginUser(this.user).subscribe(data => {
      this.service.saveUser(data.jwttoken, data.userName);
      this.router.navigate(["/dashboard"])
    },
    error => {
      console.log("Some Error occur");
      console.log(error);
      this.message="Wrong Credential, please enter valid email and password";
    }
    )
  }
}
