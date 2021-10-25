import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { applicationRoutes } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { AuthInterceptorProviders } from './services/auth.interceptor';
import { AdmindashboardComponent } from './admin/admin-dashboard/admin-dashboard.component';
import { UserAdmindashboardComponent } from './user/user-dashboard/user-dashboard.component';
import {MatCardModule} from '@angular/material/card';
import {MatListModule} from '@angular/material/list';
import {MatIconModule} from '@angular/material/icon';
import {MatRadioModule} from '@angular/material/radio';
import {MatSelectModule} from '@angular/material/select';
import { AdminsidepanelComponent } from './admin/admin-sidepanel/admin-sidepanel.component';
import { UsersidepanelComponent } from './user/user-sidepanel/user-sidepanel.component';
import { AddmedicineComponent } from './admin/add-medicine/add-medicine.component';
import { AdmhomeComponent } from './admin/admin-home/admin-home.component';
import { ViewmedicineComponent } from './admin/view-medicine/view-medicine.component';
import { UpdatemedicineComponent } from './admin/update-medicine/update-medicine.component';
import { RemovemedicineComponent } from './admin/remove-medicine/remove-medicine.component';
import { UserhomeComponent } from './user/user-home/user-home.component';
import { ShowproductsComponent } from './user/show-products/show-products.component';
import { UsercartComponent } from './user/user-cart/user-cart.component';
import { UseraddtocartComponent } from './user/user-addtocart/user-addtocart.component';
import { EditaddressComponent } from './user/edit-address/edit-address.component';
import { EditpasswordComponent } from './user/edit-password/edit-password.component';
import { UsercheckoutComponent } from './user/user-checkout/user-checkout.component';
import { PurchasesumComponent } from './user/user-purchases/user-purchases.component';
import { MatSliderModule } from '@angular/material/slider';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';

import { MatFormFieldModule } from '@angular/material/form-field';



@NgModule({


  declarations: [

    AppComponent,
    NavBarComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    AdmindashboardComponent,
    UserAdmindashboardComponent,
    AdminsidepanelComponent,
    UsersidepanelComponent,
    AddmedicineComponent,
    AdmhomeComponent,
    ViewmedicineComponent,
    UpdatemedicineComponent,
    RemovemedicineComponent,
    UserhomeComponent,
    ShowproductsComponent,
    UsercartComponent,
    UseraddtocartComponent,
    EditaddressComponent,
    EditpasswordComponent,
    UsercheckoutComponent,
    PurchasesumComponent,
    AboutComponent,
    ContactComponent
  ],
  imports: [
    
    MatFormFieldModule,
    MatToolbarModule,
    MatSidenavModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    MatInputModule,
    MatButtonModule,
    MatSnackBarModule,
    MatSliderModule,
    MatRadioModule,
    MatCardModule,
    MatIconModule,
    MatListModule,
    MatSelectModule,
    RouterModule.forRoot(applicationRoutes)
  ],

  exports: [ MatFormFieldModule, MatInputModule ],

  providers: [AuthInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
