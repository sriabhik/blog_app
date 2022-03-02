import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-navbar-logged-in',
  templateUrl: './navbar-logged-in.component.html',
  styleUrls: ['./navbar-logged-in.component.css']
})
export class NavbarLoggedInComponent implements OnInit {
  isLoggedIn = false;
  user = null;
  constructor(public login:LoginService,private router:Router) { }

  ngOnInit(): void {
    this.isLoggedIn= this.login.isLoggedIn();
    this.user = this.login.getUser();
    this.login.loginStatusSubject.asObservable().subscribe((data: any)=>{
      this.isLoggedIn= this.login.isLoggedIn();
      this.user = this.login.getUser();
    });
  }
  public logout(){
    this.login.logout();
    this.isLoggedIn=false;
    this.user=null;
    
    this.router.navigate([''])
    // window.location.reload();
    // window.location.href="/login"
  }

}
