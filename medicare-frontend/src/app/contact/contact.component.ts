import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../services/user.service';
import Swal from 'sweetalert2';
import { LoginService } from '../services/login.service';


@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})


export class ContactComponent implements OnInit {

  constructor(public userService:UserService, public loginService:LoginService ) { }

  public user={
    firstName:'',
    lastName:'',
    email:'',
    phone:'',
    address:'',
    username:'',
    password:'',
  }

  userName:any = "";
  from:any = this.loginService.getUser();
  fromName:any;
  subject:any;
  body:any;

  ngOnInit() {
    //---------------------------------
    this.user = this.loginService.currentUser;
  
    //---------------------------------
    if(this.user != null){
      this.from= this.user.email;
    }else{
      this.from= "UNKNOWN EMAIL";
    }

    //---------------------------------
    if(this.loginService.currentUser != null){
      this.fromName= this.user.firstName;
    }else{
      this.fromName= "UNKNOWN FIRSTNAME";
    }

    //---------------------------------
    this.subject="CUSTOMER MEDICARE EMAIL";
    this.body="";
  }


  emailJSON={
    from:'',
    fromName:'',
    subject:'',
    body:''
  }


  onSubmit(f: NgForm) {

    //addding user -- addUSer() -> UserService

    this.emailJSON.from=f.value.from;
    this.emailJSON.fromName=f.value.fromName;
    this.emailJSON.subject=f.value.subject;
    this.emailJSON.body=f.value.body;

    this.userService.sendEmail(this.emailJSON).subscribe(
      //on sucess
       (data)=>{
        console.log(this.emailJSON);
        Swal.fire('Success', `CONGRATULATIONS - ${this.from} - Your EMAIL was Sent to Medicare Support!`,'success');
       },
       //on error
       (error)=>{
           console.log(error);
           alert('[EMAIL SERVICE FAILURE]  an error occured during email processing!');
       }
    );
   }
}