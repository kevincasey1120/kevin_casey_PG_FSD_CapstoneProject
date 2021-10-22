import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public user={
    username:'',
    password:'',
    firstName:'',
    lastName:'',
    email:'',
    phone:'',
    address:''
  }
  constructor(private userService:UserService,private snack:MatSnackBar) { }

  ngOnInit(): void {
  }

  formSubmit(){
    console.log(this.user);
    if(this.user.username == '' || this.user.username == null){
      this.snack.open("Username Required!",'',
      {duration:3000,verticalPosition:'top',horizontalPosition:'end'});
      return;
    }

    if(this.user.password == '' || this.user.password == null){
      this.snack.open("Password Required!",'',
      {duration:3000,verticalPosition:'top',horizontalPosition:'end'});
      return;
    }

  
    if(this.user.firstName == '' || this.user.firstName == null){
      this.snack.open("FirstName Required!",'',
      {duration:3000,verticalPosition:'top',horizontalPosition:'end'});
      return;
    }

    if(this.user.phone == '' || this.user.phone == null){
      this.snack.open("phone-number Required!   SYNTAX: 222-333-4444",'',
      {duration:3000,verticalPosition:'top',horizontalPosition:'end'});
      return;
    }

    if(this.user.email == '' || this.user.email == null){
      this.snack.open("Email Required!   SYNTAX:  abc123@mail.com",'',
      {duration:3000,verticalPosition:'top',horizontalPosition:'end'});
      return;
    }

  
    //addding user -- addUSer() -> UserService
    this.userService.addUser(this.user).subscribe(
      //on sucess
       (data)=>{
        console.log(data);
        Swal.fire('Success', `CONGRATULATIONS - ${this.user.username} - You Are Now Registered! - You may now Login`,'success');
       },
       //on error
       (error)=>{
           console.log(error);
           alert('[REGISTRATION FAILURE]  an error has prevented registration :' + error);
       }
    );
  }
}
