import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-all-comment',
  templateUrl: './all-comment.component.html',
  styleUrls: ['./all-comment.component.css']
})
export class AllCommentComponent implements OnInit {

  constructor(private adminService:AdminService,private _router:ActivatedRoute,
    private snack:MatSnackBar) { }
  postId:any
  commentData:any
  ngOnInit(): void {
    
    this.postId  = this._router.snapshot.params['postId']
    this.adminService.getCommnetByPost(this.postId).subscribe((data:any)=>{
      this.commentData = data
      console.log(this.commentData);
      
    }
    ,(error)=>{
      this.snack.open("Something went wrong","Cancel",{duration:2000})
    })
  }

}
