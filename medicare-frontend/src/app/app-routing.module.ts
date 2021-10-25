import { Component } from "@angular/core";
import { Routes } from "@angular/router";
import { AddmedicineComponent } from "./admin/add-medicine/add-medicine.component";
import { AdmhomeComponent } from "./admin/admin-home/admin-home.component";
import { AdmindashboardComponent } from "./admin/admin-dashboard/admin-dashboard.component";
import { RemovemedicineComponent } from "./admin/remove-medicine/remove-medicine.component";
import { UpdatemedicineComponent } from "./admin/update-medicine/update-medicine.component";
import { ViewmedicineComponent } from "./admin/view-medicine/view-medicine.component";
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from "./login/login.component";
import { AdminGuard } from "./services/admin.guard";
import { UserGuard } from "./services/user.guard";
import { RegisterComponent } from "./register/register.component";
import { AboutComponent } from "./about/about.component";
import { ContactComponent } from "./contact/contact.component";
import { UseraddtocartComponent } from "./user/user-addtocart/user-addtocart.component";
import { UsercheckoutComponent } from "./user/user-checkout/user-checkout.component";
import { EditaddressComponent } from "./user/edit-address/edit-address.component";
import { EditpasswordComponent } from "./user/edit-password/edit-password.component";
import { PurchasesumComponent } from "./user/user-purchases/user-purchases.component";
import { UserAdmindashboardComponent } from "./user/user-dashboard/user-dashboard.component";
import { UsercartComponent } from "./user/user-cart/user-cart.component";
import { UserhomeComponent } from "./user/user-home/user-home.component";
import { ShowproductsComponent } from "./user/show-products/show-products.component";


export const applicationRoutes : Routes = [
    { path : '', component : HomeComponent, pathMatch:'full'},
    { path : 'home', component : HomeComponent, pathMatch:'full'},
    { path : 'about', component : AboutComponent, pathMatch:'full'},
    { path : 'contact', component : ContactComponent, pathMatch:'full'},
    { path : 'register', component : RegisterComponent, pathMatch:'full' },
    { path : 'about', component : AboutComponent, pathMatch:'full' },
    { path : 'contact', component : ContactComponent, pathMatch:'full' },
    { path : 'login', component : LoginComponent, pathMatch:'full' },
    { path : 'admin', component : AdmindashboardComponent, canActivate: [AdminGuard],
      children:[{ path:'',redirectTo:'home',pathMatch:'full'},
                { path : 'home',component : AdmhomeComponent, pathMatch:'full',canActivate:[AdminGuard] },
                { path : 'about',component : AboutComponent, pathMatch:'full',canActivate:[AdminGuard] },
                { path : 'contact',component : ContactComponent, pathMatch:'full',canActivate:[AdminGuard] },
                { path: 'add-medicine',component : AddmedicineComponent, pathMatch:'full',canActivate:[AdminGuard]  },
                { path: 'view-medicines', component : ViewmedicineComponent, pathMatch:'full' ,canActivate:[AdminGuard]},
                { path:'update/:medid', component : UpdatemedicineComponent, pathMatch:'full', canActivate : [AdminGuard] },
                { path:'remove/:medid', component : RemovemedicineComponent, pathMatch:'full', canActivate : [AdminGuard] }
                  ]  },
    { path : 'user', component : UserAdmindashboardComponent, canActivate: [UserGuard],
      children:[ { path:'',redirectTo:'home',pathMatch:'full'},
      { path: 'checkout',component : UsercheckoutComponent,pathMatch : 'full', canActivate : [UserGuard]},
      { path: 'purchase-summary',component : PurchasesumComponent, pathMatch : 'full', canActivate : [UserGuard]},
      { path: 'edit-address',component : EditaddressComponent, pathMatch : 'full', canActivate : [UserGuard]},
      { path: 'edit-password',component : EditpasswordComponent, pathMatch : 'full', canActivate : [UserGuard]},
      { path : 'home',component : UserhomeComponent, pathMatch:'full',canActivate : [UserGuard]},
      { path : 'about',component : AboutComponent, pathMatch:'full',canActivate : [UserGuard]},
      { path : 'contact',component : ContactComponent, pathMatch:'full',canActivate : [UserGuard]},
      { path : 'view-medicines',component : ShowproductsComponent,pathMatch :'full',canActivate:[UserGuard]},
      { path : 'cart',component : UsercartComponent, pathMatch :'full',canActivate:[UserGuard]},
      { path : 'addtocart/:medid',component : UseraddtocartComponent, pathMatch :'full',canActivate:[UserGuard]}
      ]
    }
]