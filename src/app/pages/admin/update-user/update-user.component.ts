import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  id:any
  user:any
  userUpdate ={
    name:'',
    email:'',
    password:'',
    about:''
  }
  constructor(
    private _route:ActivatedRoute,
    private admin:AdminService,
    private snack:MatSnackBar
  ) { }

  ngOnInit(): void {
   
    this.id = this._route.snapshot.params['id']
    this.admin.getUserById(this.id).subscribe((data:any)=>{
      this.user= data
      console.log(data);
      
    },
    (error)=>{
      this.snack.open("Something went wrong","Cancel",{duration:2000})
    })
  }

  formSubmit(){
    this.userUpdate.name = this.user.name
    this.userUpdate.about=this.user.about
    this.userUpdate.email=this.user.email
    this.userUpdate.password=this.user.password
    if(this.user.email==''||this.user.email==null){
      this.snack.open("Email Required","Cancel",
      {duration:2000})
        return;
    }
    if(!this.user.email.includes(".")){
      this.snack.open("Invalid Email ","Cancel",
      {duration:2000})
        return;
    }
    if(this.user.name ==''||this.user.name==null){
      this.snack.open("Name Required","Cancel",
      {duration:2000}) 
        return;
    }
    
    if(this.user.about ==''||this.user.about==null){
      this.snack.open("Name Required","Cancel",
      {duration:2000}) 
        return;
    }
    console.log(this.userUpdate);
    
    this.admin.updateUser(this.user,this.id).subscribe((data)=>{
  
      this.snack.open("Successfully Updated","Cancel",{duration:2000})
    },
    (error)=>{
      if(error.status==404){
        this.snack.open("Email address already register","Cancel",{duration:2000})
      }
      else
      this.snack.open("Something Went Wrong,Try Again","Cancel",{duration:2000})
    })
  }
}
