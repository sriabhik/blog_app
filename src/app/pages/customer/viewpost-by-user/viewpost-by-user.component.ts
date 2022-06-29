import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-viewpost-by-user',
  templateUrl: './viewpost-by-user.component.html',
  styleUrls: ['./viewpost-by-user.component.css']
})
export class ViewpostByUserComponent implements OnInit {

  constructor(private snack:MatSnackBar,private customerService:CustomerService,private _route:ActivatedRoute) { }
  postData:any
  uid:any
  ngOnInit(): void {
    this.uid= this._route.snapshot.params['id']
    this.customerService.getPostByUId(this.uid).subscribe((data)=>{
      console.log(data);
      
      this.postData=data
    },
    (error)=>{
      this.snack.open("Soemthing went wrong","Cancel",{duration:3000})
    })
  }

}
