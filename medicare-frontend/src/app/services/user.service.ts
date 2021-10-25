import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, SystemJsNgModuleLoader } from '@angular/core';
import baseUrl from './helper';
import { LoginService } from '../services/login.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient, public loginService:LoginService ) { 
   }

  //------------------------------------------------------------
  // ELASTIC EMAIL (SENDER)
  // String from (this is the senders email), String fromName, String subject, String 
  //------------------------------------------------------------
  public sendEmail(emailJSON:any){

    console.log("ANG[USER SERVICES]  (sendEmail)  begin new post "+emailJSON);

    return this.http.post(`${baseUrl}/user/sendemail`,emailJSON);
   }











   

   //-----------------------------
   public createUser(user:any){
    console.log("ANG[USER SERVICES]  (createUser)  begin new post");
     return this.http.post(`${baseUrl}/user/`, user);
   }


   public getUser(userid:number){
    console.log("ANG[USER SERVICES]  (getUser)  begin new get   USERID = ("+userid+")");
    return this.http.get(`${baseUrl}/user/${userid}`);
   }

   

   public deleteUser(userid:number){
    console.log("ANG[USER SERVICES]  (deleteUser)  begin new get");
    return this.http.get(`${baseUrl}/user/${userid}`);
   }

   public getMedicines(){
    console.log("ANG[USER SERVICES]  (getMedicines)  begin new get");
     return this.http.get(`${baseUrl}/user/getmedicines`);
   }


   public getMedicineByID(id:number){
    if(id == undefined){
      console.log("ANG[USER SERVICES -- ERROR --]  (getMedicineByID --> "+id+")  is UNDEFINED -- This Should Not Happen --");
    }else{
      console.log("ANG[USER SERVICES]  (getMedicineByID --> "+id+")  begin new get");
    }
    return this.http.get(`${baseUrl}/user/getmedicine/${id}`);
   }

   public searchByCategory(search:any){
    console.log("ANG[USER SERVICES]  (searchByCategory)  begin new get ("+search+")");
    return this.http.get(`${baseUrl}/user/getmedicinesbycat/${search}`);
   }

   public searchByName(medname:any){
    console.log("ANG[USER SERVICES]  (searchByName)  begin new get ("+medname+")");
    return this.http.get(`${baseUrl}/user/getmedicinesbyname/${medname}`);
   }


   public addToCart(addToCartJSON:any){
    console.log("ANG[USER SERVICES]  (addToCart)  begin new get");
     return this.http.post(`${baseUrl}/user/addtocart`,addToCartJSON);
   }
   
   public updateQuantity(chQuantity:any){
    console.log("ANG[USER SERVICES]  (updateQuantity)  begin new get");
     return this.http.post(`${baseUrl}/user/updatequantity`,chQuantity);
   }

   public removeItem(itemsid:number){
    console.log("ANG[USER SERVICES]  (removeItem)  begin new delete");
     return this.http.delete(`${baseUrl}/user/removeitem/${itemsid}`);
   }
   public getUserCart(userid:number){
    console.log("ANG[USER SERVICES]  (getUserCart)  begin new get");
     return this.http.get(`${baseUrl}/user/getusercart/${userid}`);
   }

   public updateAddress(updateDetails:any){
      console.log("ANG[USER SERVICES]  (updateAddress)  begin new post");
       return this.http.post(`${baseUrl}/user/updateaddress`,updateDetails);
   }

   public updatePassword(updateDetails:any){
    console.log("ANG[USER SERVICES]  (updatePassword)  begin new post");
    return this.http.post(`${baseUrl}/user/updatepassword`,updateDetails);
   }

   public purchaseItems(id:number){
    console.log("ANG[USER SERVICES]  (purchaseItems)  begin new post");
      return this.http.post(`${baseUrl}/user/purchaseitems/${id}`,null);
   }

}
