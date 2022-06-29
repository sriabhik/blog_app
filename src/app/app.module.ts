import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { SignupComponent } from './pages/auth/signup/signup.component';


import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatCardModule} from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { LoginComponent } from './pages/auth/login/login.component';
import { authInterceptorProviders } from './service/auth.interceptor';
import { HomepageComponent } from './pages/customer/homepage/homepage.component';
import { AddcenterComponent } from './pages/admin/addcenter/addcenter.component';
import { NavbarLoggedInComponent } from './components/navbar-logged-in/navbar-logged-in.component';
import { CustomerNavbarComponent } from './components/customer-navbar/customer-navbar.component';

import {FlexLayoutModule} from "@angular/flex-layout"
import { ForgetComponent } from './pages/auth/forget/forget.component';
import { OtpComponent } from './pages/auth/otp/otp.component';
import { SetPasswordComponent } from './pages/auth/set-password/set-password.component';
import {MatMenuModule} from '@angular/material/menu';
import { ReactiveFormsModule } from '@angular/forms';
import { AddCategoryComponent } from './pages/admin/add-category/add-category.component';
import { DisplayUserComponent } from './pages/admin/display-user/display-user.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { UpdateUserComponent } from './pages/admin/update-user/update-user.component';
import { CategoryComponent } from './pages/admin/category/category.component';
import { UserCategoryComponent } from './pages/customer/user-category/user-category.component';
import { AddPostComponent } from './pages/customer/add-post/add-post.component';
import { ViewpostByUserComponent } from './pages/customer/viewpost-by-user/viewpost-by-user.component';
import { AllPostComponent } from './pages/customer/all-post/all-post.component';
import { AllPostAdminComponent } from './pages/admin/all-post-admin/all-post-admin.component';
import { PostByCategoryComponent } from './pages/admin/post-by-category/post-by-category.component';
import { PostByCategoryCustomerComponent } from './pages/customer/post-by-category-customer/post-by-category-customer.component';
import { AllCommentComponent } from './pages/admin/all-comment/all-comment.component';
import { AddCommentsComponent } from './pages/customer/add-comments/add-comments.component';
import { AllCommentUserComponent } from './pages/customer/all-comment-user/all-comment-user.component';
import { AddCommentAdminComponent } from './pages/admin/add-comment-admin/add-comment-admin.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    SignupComponent,
    LoginComponent,
    HomepageComponent,
    AddcenterComponent,
    NavbarLoggedInComponent,
    CustomerNavbarComponent,
    ForgetComponent,
    OtpComponent,
    SetPasswordComponent,
    AddCategoryComponent,
    DisplayUserComponent,
    UpdateUserComponent,
    CategoryComponent,
    UserCategoryComponent,
    AddPostComponent,
    ViewpostByUserComponent,
    AllPostComponent,
    AllPostAdminComponent,
    PostByCategoryComponent,
    PostByCategoryCustomerComponent,
    AllCommentComponent,
    AddCommentsComponent,
    AllCommentUserComponent,
    AddCommentAdminComponent,
    

    
  ],
  imports: [
    NgxPaginationModule,
    ReactiveFormsModule,
    MatMenuModule,
    FlexLayoutModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    HttpClientModule,
    MatSnackBarModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatListModule,
    MatSlideToggleModule,
    
  ],
  providers: [authInterceptorProviders,ForgetComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
