import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-all-comment-user',
  templateUrl: './all-comment-user.component.html',
  styleUrls: ['./all-comment-user.component.css']
})
export class AllCommentUserComponent implements OnInit {

  constructor(private customerS:CustomerService,private _router:ActivatedRoute,
    private snack:MatSnackBar) { }
  postId:any
  commentData:any

  ngOnInit(): void {
    this.postId  = this._router.snapshot.params['postId']
    this.customerS.getCommnetByPost(this.postId).subscribe((data:any)=>{
      this.commentData = data
      console.log(this.commentData);
      
    }
    ,(error)=>{
      this.snack.open("Something went wrong","Cancel",{duration:2000})
    })
  }
}
