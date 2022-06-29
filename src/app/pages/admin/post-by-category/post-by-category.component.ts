import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-post-by-category',
  templateUrl: './post-by-category.component.html',
  styleUrls: ['./post-by-category.component.css']
})
export class PostByCategoryComponent implements OnInit {
  post:any
  constructor(private adminService:AdminService,private _router:ActivatedRoute,private snack:MatSnackBar) { }
  cid:any
  ngOnInit(): void {
    this.cid = this._router.snapshot.params['categoryId']
    this.adminService.getPostByCategory(this.cid).subscribe((data:any)=>{
        this.post = data
    },
    (error)=>{
      this.snack.open("Something went wrong","Cancel",{duration:2000})
    })
  }


}
