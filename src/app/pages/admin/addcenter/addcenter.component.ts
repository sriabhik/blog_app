import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-addcenter',
  templateUrl: './addcenter.component.html',
  styleUrls: ['./addcenter.component.css']
})
export class AddcenterComponent implements OnInit {

  constructor(private route:Router) { }

  ngOnInit(): void {
    this.route.navigate(['/admin/allPost'])
  }

}
