import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

import { UserService } from 'src/app/service/user.service';

interface datatype {
 
  email:string,
  username:string,
  mobileNumber:number,
  password : any,
  confirmPassword:any,
  userRole:string
}

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  public user={
   
    email:'',
    username:'',
    mobileNumber:'',
    password : '',
    confirmPassword:'',
    userRole:'',
    name:''
  }
  constructor(private userService :UserService,private snack:MatSnackBar) { }

  ngOnInit(): void {
  }
  formSubmit(){
    if((this.user.userRole!=='admin' && this.user.userRole!='customer') ){
      this.snack.open("Enter admin/customer","Cancel",
      {duration:2000})
      this.f()
        return;
    }
    if(this.user.username ==''||this.user.username==null){
      this.snack.open("Username Required","Cancel",
      {duration:2000})
      this.f()
        return;
    }
    if(this.user.email==''||this.user.email==null){
      this.snack.open("Email Required","Cancel",
      {duration:2000})
      this.f()
        return;
    }
    if(this.user.mobileNumber ==''||this.user.mobileNumber==null){
      this.snack.open("mobileNumber Required","Cancel",
      {duration:2000})
      
      this.f()
        return;
    }
    if(this.user.password ==''||this.user.password==null){
      this.snack.open("password Required","Cancel",
      {duration:2000})
       this.f()
        return;
    }
    if(this.user.confirmPassword ==''||this.user.confirmPassword==null){
      this.snack.open("Enter Confirm password Required","Cancel",
      {duration:2000})
      this.f()
        return;
    }
    if(this.user.confirmPassword == this.user.password){
      this.userService.addUser(this.user,this.user.userRole).subscribe(
        (data)=>{
          //success
        
          this.snack.open("Registration Successfull","Cancel",{
            duration:2000})
          
        },
        (error)=>
        {
          //fail
          console.log(error)
          this.snack.open("User Already Registered Or  Try again","Cancel",{duration:2000,verticalPosition:'top',horizontalPosition:'left'})    
        }
      );
    }
    else{
      this.f()
      this.snack.open("password and confirmPassword mismatch","cancel",{duration:3000})
      
    }
    
  }
  f(){
    this.user={
   
      email:'',
      username:'',
      mobileNumber:'',
      password : '',
      confirmPassword:'',
      userRole:'',
      name:''
    }
  }
}
