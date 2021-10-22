import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  isLoggedIn = false;
  user = null;

  constructor(public loginService:LoginService) { }

  ngOnInit(): void {
    
    this.loginService.loginStatusSubject.asObservable().subscribe(
      (data:any)=>{
        this.isLoggedIn=this.loginService.isLoggedIn();
        this.user=this.loginService.getUser();
        console.log(`navbar isLoggedin = ${this.isLoggedIn},user = ${this.user} `);
      }
    );
  }

  public logout(){
     this.loginService.logout();
     window.location.reload();
  }
}
