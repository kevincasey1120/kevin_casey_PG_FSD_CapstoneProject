import { Component } from "@angular/core";
import { Routes } from "@angular/router";
import { AddmedicineComponent } from "./admin/addmedicine/addmedicine.component";
import { AdmhomeComponent } from "./admin/admhome/admhome.component";
import { DashboardComponent } from "./admin/dashboard/dashboard.component";
import { RemmedicineComponent } from "./admin/remmedicine/remmedicine.component";
import { UpdmedicineComponent } from "./admin/updmedicine/updmedicine.component";
import { ViewmedicineComponent } from "./admin/viewmedicine/viewmedicine.component";
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from "./login/login.component";
import { AdminGuard } from "./services/admin.guard";
import { UserGuard } from "./services/user.guard";
import { SignupComponent } from "./register/register.component";
import { AddtocartComponent } from "./user/addtocart/addtocart.component";
import { CheckoutComponent } from "./user/checkout/checkout.component";
import { EditaddrComponent } from "./user/editaddr/editaddr.component";
import { EditpassComponent } from "./user/editpass/editpass.component";
import { PurchasesumComponent } from "./user/purchasesum/purchasesum.component";
import { UserDashboardComponent } from "./user/user-dashboard/user-dashboard.component";
import { UsercartComponent } from "./user/usercart/usercart.component";
import { UserhomeComponent } from "./user/userhome/userhome.component";
import { ViewmedicinesComponent } from "./user/viewmedicines/viewmedicines.component";

export const applicationRoutes : Routes = [
    { path : '', component : HomeComponent, pathMatch:'full'},
    { path : 'register', component : SignupComponent, pathMatch:'full' },
    { path : 'login', component : LoginComponent, pathMatch:'full' },
    { path : 'admin', component : DashboardComponent, canActivate: [AdminGuard],
      children:[{ path:'',redirectTo:'home',pathMatch:'full'},
                { path : 'home',component : AdmhomeComponent, pathMatch:'full',canActivate:[AdminGuard] },
                { path: 'add-medicine',component : AddmedicineComponent, pathMatch:'full',canActivate:[AdminGuard]  },
                { path: 'view-medicines', component : ViewmedicineComponent, pathMatch:'full' ,canActivate:[AdminGuard]},
                { path:'update/:medId', component : UpdmedicineComponent, pathMatch:'full', canActivate : [AdminGuard] },
                { path:'remove/:medId', component : RemmedicineComponent, pathMatch:'full', canActivate : [AdminGuard] }
                  ]  },
    { path : 'user', component : UserDashboardComponent, canActivate: [UserGuard],
      children:[ { path:'',redirectTo:'home',pathMatch:'full'},
      { path: 'checkout',component : CheckoutComponent,pathMatch : 'full', canActivate : [UserGuard]},
      { path: 'purchase-summary',component : PurchasesumComponent, pathMatch : 'full', canActivate : [UserGuard]},
      { path: 'edit-address',component : EditaddrComponent, pathMatch : 'full', canActivate : [UserGuard]},
      { path: 'edit-password',component : EditpassComponent, pathMatch : 'full', canActivate : [UserGuard]},
      { path : 'home',component : UserhomeComponent, pathMatch:'full',canActivate : [UserGuard]},
      { path : 'view-medicines',component : ViewmedicinesComponent,pathMatch :'full',canActivate:[UserGuard]},
      { path : 'cart',component : UsercartComponent, pathMatch :'full',canActivate:[UserGuard]},
      { path : 'addtocart/:medId',component : AddtocartComponent, pathMatch :'full',canActivate:[UserGuard]}
      ]
    }
]