import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http:HttpClient) { }
  public getCategory(pageNumber: number,pageSize:number){
    return this.http.get(`${baseUrl}/api/categories/getAllCategory?pageNumber=${pageNumber}&pageSize=${pageSize}&sortBy=categoryId&sortDir=asc`)
  }
  public addPost(post:any,id:any,categoryId:any){
    return this.http.post(`${baseUrl}/api/user/${id}/category/${categoryId}/posts`,post);
  }
  public getPostByUId(uid:any){
    return this.http.get(`${baseUrl}/api/getPostByUser/${uid}`)
  }
  public getPost(pageNumber: number,pageSize:number){
    return this.http.get(`${baseUrl}/api/getAllPost?pageNumber=${pageNumber}&pageSize=${pageSize}&sortBy=postId&sortDir=asc`)
  }
  public getPostByCategory(cid:any){
    return this.http.get(`${baseUrl}/api/getPostByCategory/${cid}`)
  }
  public addComment(data:any,postId:any){
    return this.http.post(`${baseUrl}/api/createComment/${postId}`,data)
  }
  public getCommnetByPost(postId:any){
    return this.http.get(`${baseUrl}/api/getCommentByPost/${postId}`)
  }
}
