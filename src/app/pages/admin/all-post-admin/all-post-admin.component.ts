import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-all-post-admin',
  templateUrl: './all-post-admin.component.html',
  styleUrls: ['./all-post-admin.component.css']
})
export class AllPostAdminComponent implements OnInit {
  postData:any;
  
  p: number = 0
  pageSize:number = 1
  total: number = 0;
  constructor(private adminService:AdminService) { }

  ngOnInit(): void {
    this.getCategorys();
  }
  getCategorys(){
   
    this.adminService.getPost(this.p,this.pageSize)
   
      .subscribe((response: any) => {
        
        this.postData = response.content;
        console.log(this.postData);
        
        this.total = response.totalElements;
      });
  } 
  pageChangeEvent(event: number){
    event = event - 1
    this.p = event;
    this.getCategorys();
    this.p = this.p+1;
  }

}
