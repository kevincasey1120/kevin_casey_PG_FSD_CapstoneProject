import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';


@Injectable({
  providedIn: 'root'
})


export class AdminService {

public currentMed:any = null;

  medicine={
    medid:'',
    medname:'',
    price:'',
    enabled:true,
    category:'',
    quantity:'',
    dosage:'',
    description:''
  };

  constructor(private http:HttpClient) { }

  public addMedicine(medicine:any){
     return this.http.post(`${baseUrl}/admin/add-medicine`,medicine);
  }
 
  public retrieveAllMedicines(){
    return this.http.get(`${baseUrl}/admin/getmedicines`);
  }

  public getMedicineByID(id:number){
    if(id == undefined){
      console.log("ANG[USER SERVICES -- ERROR --]  (getMedicineByID --> "+id+")  is UNDEFINED -- This Should Not Happen --");
    }else{
      console.log("ANG[USER SERVICES]  (getMedicineByID --> "+id+")  begin new get");
    }
    return this.http.get(`${baseUrl}/user/getmedicine/${id}`);
  }

  public updateMedicine(medid:number,medicine:any){
    return this.http.post(`${baseUrl}/admin/update-medicine/${medid}`,medicine);
  }

  public removeMedicine(medid:number){
     return this.http.delete(`${baseUrl}/admin/remove-medicine/${medid}`);
  }

}
 