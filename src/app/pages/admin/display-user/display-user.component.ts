import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-display-user',
  templateUrl: './display-user.component.html',
  styleUrls: ['./display-user.component.css']
})
export class DisplayUserComponent implements OnInit {
  users:any;
  p: number = 0
  pageSize:number = 5
  total: number = 0;
  constructor(private admin:AdminService,private snack:MatSnackBar) { }

  ngOnInit(): void {
    this.getUser();
  }
  getUser(){
    this.admin.getUsers(this.p,this.pageSize)
   
      .subscribe((response: any) => {
        
        this.users = response.content;
        console.log(this.users)
        this.total = response.totalElements;
      });
  } 

  pageChangeEvent(event: number){
    event = event - 1
    this.p = event;
    this.getUser();
    this.p = this.p+1;
  }
  delete(id:any){
    this.admin.deleteUser(id).subscribe((data:any)=>{
      this.users = this.users.filter(
        (UserDto: any) =>
          UserDto.id != id
          );
      this.snack.open("User deleted !","Cancel",{duration:3000})
    }
    ,(errror)=>{
      this.snack.open("Something went wrong","Cancel",{duration:3000})
    })
  }
}
