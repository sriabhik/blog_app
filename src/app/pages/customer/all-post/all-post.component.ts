import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-all-post',
  templateUrl: './all-post.component.html',
  styleUrls: ['./all-post.component.css']
})
export class AllPostComponent implements OnInit {

  postData:any;
  
  p: number = 0
  pageSize:number = 1
  total: number = 0;
  constructor(
    private customerService:CustomerService,
   
    ) { }

  ngOnInit(): void {
    this.getCategorys();
   
  }
 
  
  getCategorys(){
   
    this.customerService.getPost(this.p,this.pageSize)
   
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
