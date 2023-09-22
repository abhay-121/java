import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import {User} from '../model/User';
import { Router} from '@angular/router';
import { RegistrationService} from '../services/registration.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user:User={
    email:"",
    firstName:"",
    lastName:"",
    password:""
  }
  message = "";
  constructor(private router: Router, private service:RegistrationService) { }

  ngOnInit(): void {
  }

  userRegistration(){
    this.service.registerUserFromRemote(this.user).subscribe(data =>{
      console.log(this.user);
      alert("Registration successful");
      this.router.navigate(['/login']);
    },
    error => {
      console.log("Some Error Occur");
      this.message="This email already registered."
    }    
    )
  }
}
