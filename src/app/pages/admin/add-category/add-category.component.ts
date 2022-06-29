import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {

  public Category=
    {
      categoryTitle:'',
      categoryDescription:''
    }
  
  constructor(
    private snack:MatSnackBar,
    private adminService:AdminService
  ) { }

  ngOnInit(): void {
  }
  formSubmit(){
  
    if(this.Category.categoryTitle==null || this.Category.categoryTitle==''){
      this.snack.open("Title description required","Cancel",{duration:3000})
      return
    }
    if(this.Category.categoryDescription==null || this.Category.categoryDescription==''){
        this.snack.open("Category description required","Cancel",{duration:3000})
        return
    }
    this.adminService.addCategory(this.Category).subscribe((data)=>{
      this.snack.open("Category added successfully","Cancel",{duration:3000})
    },
    (error)=>{
      if(error.status==400){
        this.snack.open("Description must be of minimum length 20!","Cancel",{duration:3000})
      }
      else
      this.snack.open("Something went wrong!!","Cancel",{duration:3000})
    })
  }

}
