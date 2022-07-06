import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
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
  public showPassword: 
  boolean = false;

  public showPassword2: 
  boolean = false;
  public user={
    name:'',
    email:'',
    password : '',
    about:''
    
  }
  
  constructor(  private userService :UserService,
                private snack:MatSnackBar,
              
                ) { } 

  ngOnInit(): void {
  }
  formSubmit(){
   
  
    if(this.user.email==''||this.user.email==null || this.user.email.length < 5){
      this.snack.open("Email Required or invalid email","Cancel",
      {duration:2000})
        return;
    }
    if(!this.user.email.includes(".")){
      this.snack.open("Invalid Email ","Cancel",
      {duration:2000})
        return;
    }
    if(!this.user.email.includes(".com") || !this.user.email.includes("@")){
      this.snack.open("Invalid Email ","Cancel",
      {duration:2000})
        return;
    }
    if(this.user.name ==''||this.user.name==null){
      this.snack.open("Name Required","Cancel",
      {duration:2000}) 
        return;
    }
    if(this.user.name.length<3){
      this.snack.open("Invalid name","Cancel",
      {duration:2000}) 
        return;
    }
    if( this.user.password ==''||this.user.password==null){
      this.snack.open("password Required","Cancel",
      {duration:2000})
        return;
    }
    if(this.user.password.length < 8 ){
      this.snack.open("password  length should greater than  8 ","Cancel",
      {duration:2000})
        return;
    }
    this.userService.addUser(this.user).subscribe((data)=>{
      this.snack.open("Successfully Register","Cancel",{duration:2000})
      this.f()
    },
    (error)=>{
      if(error.status==404){
        this.snack.open("Email address already register","Cancel",{duration:2000})
      }
      else
      this.snack.open("Something Went Wrong,Try Again","Cancel",{duration:2000})
    })
    
  }
  f(){
    this.user={
   
      email:'',
      password : '',
      about:'',
      name:''
    }
  }
 
  public togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }
  public togglePasswordVisibility1(): void {
    this.showPassword2 = !this.showPassword2;
  }
}
