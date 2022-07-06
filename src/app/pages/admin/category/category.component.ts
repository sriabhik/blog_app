import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  categorys:any;
  p: number = 0
  pageSize:number = 2
  total: number = 0;
  constructor(private admin:AdminService,private snack:MatSnackBar) { }

  ngOnInit(): void {
    this.getCategorys()
  }
  getCategorys(){
    this.admin.getCategory(this.p,this.pageSize)
   
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
  delete(cid:any){
  
    this.admin.deleteCategory(cid).subscribe((success)=>{
      // this.categorys = this.categorys.filter(
      //   (CategoryDto: any) =>
      //     CategoryDto.categoryId != cid
      //     );
      this.getCategorys()
      window.location.reload()
      this.snack.open("Category deletion successfull","Cancel",{duration:3000})
    },
    (error)=>{
      this.snack.open("Something went wrong !","Cancel",{duration:3000})
    })
  }
}
