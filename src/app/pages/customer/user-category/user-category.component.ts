import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/service/customer.service';
import { LoginService } from 'src/app/service/login.service';
@Component({
  selector: 'app-user-category',
  templateUrl: './user-category.component.html',
  styleUrls: ['./user-category.component.css']
})
export class UserCategoryComponent implements OnInit {
  categorys:any;
  user:any
  p: number = 0
  pageSize:number = 3
  total: number = 0;
  constructor(
    private customerService:CustomerService,
    private login:LoginService
    ) { }

  ngOnInit(): void {
    this.getCategorys();
    this.login.getCurrentUser().subscribe((data:any)=>{
        this.user = data
    })
  }
 
  
  getCategorys(){
    console.log(this.user);
    this.customerService.getCategory(this.p,this.pageSize)
   
      .subscribe((response: any) => {
        
        this.categorys = response.content;
        console.log(this.categorys);
        
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
