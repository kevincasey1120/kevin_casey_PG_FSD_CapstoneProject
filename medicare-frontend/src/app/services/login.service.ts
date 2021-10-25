import { HttpClient } from '@angular/common/http';
import { BuiltinFunctionCall } from '@angular/compiler/src/compiler_util/expression_converter';
import { Injectable, SystemJsNgModuleLoader } from '@angular/core';
import { Subject } from 'rxjs';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class LoginService {


   public currentUser: any = this.getUser();


   public loginStatusSubject=new Subject<boolean>();

   //-----------------------------
   public userLoginStatusSubject=new Subject<boolean>();

   //-----------------------------
   public adminLoginStatusSubject=new Subject<boolean>();



  constructor(private http:HttpClient) { }

  //[generateToken]generate token
  public generateToken(loginData :any){
     return this.http.post(`${baseUrl}/generate-token`,loginData);
  }
  
  //[getCurrentUser] Get current user: which is logged in
  public getCurrentUser(){
      return this.http.get(`${baseUrl}/current-user`);
  }

  //[loginUser] Set token in local storage
  public loginUser(token:any){
    localStorage.setItem("token",token);
    return true;
  }

  //[isLoggedIn] user is logged in or not
  public isLoggedIn(){
    let tokenStr = localStorage.getItem("token");
    if(tokenStr==undefined || tokenStr== '' || tokenStr ==null){
      return false;
    }else{       
    
      return true;

    }
  }

  //[logout] remove token from local storage
  public logout(){
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    return true;
  }

  //[getToken] get token
  public getToken(){
    return localStorage.getItem("token");
  }

  //[getUser] Set setUser

  public setUser(user: any){
    localStorage.setItem("user",JSON.stringify(user));
   }

  //[getUser] get UserDetails
  public getUser(){
    let userStr=localStorage.getItem("user");
    if(userStr!=null){
      return JSON.parse(userStr);
    }else{
      this.logout();
      return null;
    }
  }

  //[getUserRole] get User Role
  public getUserRole()
  {
    let user=this.getUser();

    console.log("YOUR AUTHORITY is ("+user.authorities[0]+")   ("+user.authorities[0].authority+")")

    return user.authorities[0].authority;
  }

}
