import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-add-comments',
  templateUrl: './add-comments.component.html',
  styleUrls: ['./add-comments.component.css']
})
export class AddCommentsComponent implements OnInit {

  constructor(private _router:ActivatedRoute,private customerS:CustomerService,
    private snale:MatSnackBar
    ) { }
  id:any
  commentData ={
    content:''
  }
  ngOnInit(): void {
    this.id = this._router.snapshot.params['postId']
    
  }
    formSubmit(){
      if(this.commentData.content == '' || this.commentData.content == null){
          this.snale.open("Comment can't be empty","Cancel",{duration:2000})
          return
      }
      console.log(this.id);
      console.log(this.commentData);
      
      
      this.customerS.addComment(this.commentData,this.id).subscribe((success)=>{
        this.snale.open("Comment added","Cancel",{duration:2000})
      },(error)=>{
        this.snale.open("Something went wrong","Cancel",{duration:2000})
      })
    }
}
