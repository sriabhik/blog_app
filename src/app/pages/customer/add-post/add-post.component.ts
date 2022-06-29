import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  post={
    title:'',
    content:''
  }
  constructor( private snack:MatSnackBar,private customerService:CustomerService,private _route:ActivatedRoute) { }
  uid:any
  cid:any
  ngOnInit(): void {
    this.uid= this._route.snapshot.params['id']
    this.cid = this._route.snapshot.params['categoryId']
  }
  formSubmit(){
    if(this.post.title==null || this.post.title==''){
      this.snack.open("Title required","Cancel",{duration:3000})
      return
    }
    if(this.post.content==null || this.post.content==''){
        this.snack.open("Content required","Cancel",{duration:3000})
        return
    }
    this.customerService.addPost(this.post,this.uid,this.cid).subscribe((data)=>{
      this.snack.open("Post added successfully","Cancel",{duration:3000})
    },
    (error)=>{
     
      this.snack.open("Something went wrong!!","Cancel",{duration:3000})
    })
  }
}
