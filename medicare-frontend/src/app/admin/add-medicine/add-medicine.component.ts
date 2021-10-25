import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-medicine',
  templateUrl: './add-medicine.component.html',
  styleUrls: ['./add-medicine.component.css']
})
export class AddmedicineComponent implements OnInit {

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
  constructor(private adminService:AdminService,private snack:MatSnackBar) { }

  ngOnInit(): void {
  }
   
  addMedicine(){
    console.log(this.medicine);
    if(this.medicine.medname == '' || this.medicine.medname == null){
      this.snack.open("Medicine name is required",'',
      {duration:3000,verticalPosition:'top',horizontalPosition:'end'});
      return;
    }
     
    //ADD CUSINE addMedicine() -> admin.service
    this.adminService.addMedicine(this.medicine).subscribe(
      //on sucesss
      (data:any)=>{
        console.log(data);
        Swal.fire("success",`${this.medicine.medname} is added to database`,'success');
      },
      //on error
      (error:any)=>{
        console.log(error);
        alert("Medicine already exists in database");
      }
    );

  }
}
