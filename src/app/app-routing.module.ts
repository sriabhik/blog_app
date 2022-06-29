import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCategoryComponent } from './pages/admin/add-category/add-category.component';
import { AddcenterComponent } from './pages/admin/addcenter/addcenter.component';
import { AllCommentComponent } from './pages/admin/all-comment/all-comment.component';
import { AllPostAdminComponent } from './pages/admin/all-post-admin/all-post-admin.component';
import { CategoryComponent } from './pages/admin/category/category.component';
import { DisplayUserComponent } from './pages/admin/display-user/display-user.component';
import { PostByCategoryComponent } from './pages/admin/post-by-category/post-by-category.component';
import { UpdateUserComponent } from './pages/admin/update-user/update-user.component';

import { ForgetComponent } from './pages/auth/forget/forget.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { OtpComponent } from './pages/auth/otp/otp.component';
import { SetPasswordComponent } from './pages/auth/set-password/set-password.component';
import { SignupComponent } from './pages/auth/signup/signup.component';
import { AddCommentsComponent } from './pages/customer/add-comments/add-comments.component';
import { AddPostComponent } from './pages/customer/add-post/add-post.component';
import { AllCommentUserComponent } from './pages/customer/all-comment-user/all-comment-user.component';
import { AllPostComponent } from './pages/customer/all-post/all-post.component';
import{HomepageComponent} from './pages/customer/homepage/homepage.component';
import { PostByCategoryCustomerComponent } from './pages/customer/post-by-category-customer/post-by-category-customer.component';
import { UserCategoryComponent } from './pages/customer/user-category/user-category.component';
import { ViewpostByUserComponent } from './pages/customer/viewpost-by-user/viewpost-by-user.component';
import { AdminGuard } from './service/admin.guard';
import { CustomerGuard } from './service/customer.guard';
const routes: Routes = [
  {
    path:'signup',
    component:SignupComponent,
    pathMatch:'full'
  },
 
  {
    path:'setPassword/:this.email',
    component:SetPasswordComponent,
    pathMatch:'full'
  },
  {
    path:'otp/:this.out/:this.emailData.to',
    component:OtpComponent,
    pathMatch:'full'
  },
  {
    path:'forget',
    component:ForgetComponent,
    pathMatch:'full'
  },
  {
    path:'',
    component:LoginComponent,
    pathMatch:'full'
  },

  {
    path:'admin',
    component:AddcenterComponent,
  
    canActivate:[AdminGuard],

    children:[
        {
          path:'addCategory',
          component:AddCategoryComponent,
        },
        {
          path:'displayUser',
          component:DisplayUserComponent
        },
        {
          path:'updateUser/:id',
          component:UpdateUserComponent
        },
        {
          path:'category',
          component:CategoryComponent
        },
        {
          path:'postByCategory/:categoryId',
          component:PostByCategoryComponent
        },
        {
          path:'allPost',
          component:AllPostAdminComponent
        },
        {
          path:'allComment/:postId',
          component:AllCommentComponent
        }
      ]
  },
  {
    path:'customer',
    component:HomepageComponent,
    canActivate:[CustomerGuard],
    children:[
      {
        path:'category',
        component:UserCategoryComponent
      },
      {
        path:'addPost/:id/:categoryId',
        component:AddPostComponent
      },
      {
        path:'postByUser/:id',
        component:ViewpostByUserComponent
      },
      {
        path:'allPost',
        component:AllPostComponent
      },
      {
        path:'allPost',
        component:AllPostAdminComponent
      },
      {
        path:'allPostByCategory/:categoryId',
        component:PostByCategoryCustomerComponent
      },
      {
        path:'addComment/:postId',
        component:AddCommentsComponent
      },
      {
        path:'allCommentUser/:postId',
        component:AllCommentUserComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
