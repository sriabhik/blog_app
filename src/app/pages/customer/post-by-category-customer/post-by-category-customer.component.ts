import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-post-by-category-customer',
  templateUrl: './post-by-category-customer.component.html',
  styleUrls: ['./post-by-category-customer.component.css']
})
export class PostByCategoryCustomerComponent implements OnInit {

  constructor(private _router:ActivatedRoute,private snack:MatSnackBar,private customer:CustomerService) { }
 cid:any
  post:any
  ngOnInit(): void {
    this.cid = this._router.snapshot.params['categoryId']
  this.customer.getPostByCategory(this.cid).subscribe((data:any)=>{
      this.post = data
  },
  (error)=>{
    this.snack.open("Something went wrong","Cancel",{duration:2000})
  })
  }
  
}
