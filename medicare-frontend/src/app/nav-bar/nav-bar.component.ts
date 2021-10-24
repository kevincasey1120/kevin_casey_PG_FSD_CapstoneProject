import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';


@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  user = null;

  isLoggedIn: boolean = false;
  isAdmin: boolean = false;
  isUser: boolean = false;

  constructor(public loginService:LoginService) { }


  categories =[
      "DEPRESSION",
      "BLOOD PRESSURE",
      "DIABETES",
      "ARTERY HEALTH"
  ];


  fullCategories =[
    { id: 0, cat: "ALL TYPES"},
    { id: 1, cat: "DIABETES",          A: "Glyburide",                   B: "Glipizide",        C: "Riomet",                D: "Segluromet",       E: "Segluromet"},
    { id: 2, cat: "BLOOD PRESSURE",    A: "Lisinopril",                  B: "Furosemide",       C: "Propranolol",           D: "Clonidine",        E: "Furosemide"},
    { id: 3, cat: "DEPRESSION",        A: "Bupropion",                   B: "Nortriptyline",    C: "Doxepin",               D: "Citalopram",       E: "Duloxetine"},
    { id: 4, cat: "ARTERY HEALTH",     A: "Metformin ",                  B: "Losartan",         C: "Hydrochlorothiazide",   D: "Atorvastatin",     E: "Amlodipine"},
  ];

  
  ngOnInit(): void {
    
    this.loginService.loginStatusSubject.subscribe(
      (data:any)=>{

        this.isLoggedIn=this.loginService.isLoggedIn();
        this.user=this.loginService.getUser();
      
        this.loginService.currentUser=this.loginService.getUser();
        console.log(`navbar isLoggedin = ${this.isLoggedIn},user = ${this.user} `);

        this.isAdmin = this.loginService.getUserRole() === 'ROLE_USER';
        this.isUser = this.loginService.getUserRole() === 'ROLE_ADMIN';
      }
    ); 
  }





  public logout(){
     this.loginService.logout();
     window.location.reload();
  }
}
