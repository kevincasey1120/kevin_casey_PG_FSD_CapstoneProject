import { Component, VERSION, OnInit } from '@angular/core';
import { Observable } from "rxjs";






const data = new Observable(x => {
  x.next([
    ["Citalopram", "Doxepin", "Metoclopramide", "Pantoprazole", "Omeprozole"]
  ]);
});


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})



export class HomeComponent implements OnInit {

  constructor() { }


advertisements =[
    { id: 1, name: "Citalopram",  description: "treats depression",  dosage: "5 ML", quantity: "450 ML", price: "$175.50"},
    { id: 2, name: "Doxepin",  description: "treats depression",  dosage: "5 ML", quantity: "450 ML", price: "$175.00"},
    { id: 3, name: "Metoclopramide",  description: "treats heartburn.",  dosage: "5 ML", quantity: "600 ML", price: "$48.00"},
    { id: 4, name: "Furosemide",  description: "treats high blood pressure",  dosage: "10 ML", quantity: "120 ML", price: "$10.80"},
    { id: 5, name: "Glyburide",  description: "treats type 2 diabetes.",  dosage: "6 MG", quantity: "30 tablets", price: "$6.90"}
  ];


  name = "Angular " + VERSION.major;
  //tests: any;
  ngOnInit(): void {
   // data.subscribe(x => this.tests = x);
  }
}

