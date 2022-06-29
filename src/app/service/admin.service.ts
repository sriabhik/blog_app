import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient) { }
  public addCategory(data:any){
    console.log(data)
    return this.http.post(`${baseUrl}/api/categories/`,data)
  }
  public getUsers(pageNumber: number,pageSize:number){
    return this.http.get(`${baseUrl}/api/user/getAllUser?pageNumber=${pageNumber}&pageSize=${pageSize}&sortBy=id&sortDir=asc`);
  }
  public deleteUser(id:number){
    return this.http.delete(`${baseUrl}/api/user/deleteUser/${id}`)
  }
  public getUserById(id:number){
    return this.http.get(`${baseUrl}/api/user/getUser/${id}`)
  }
  public updateUser(data:any,id:any){
    console.log(data);
    
    return this.http.put(`${baseUrl}/api/user/updateUser/${id}`,data)
  }
  public getCategory(pageNumber: number,pageSize:number){
    return this.http.get(`${baseUrl}/api/categories/getAllCategory?pageNumber=${pageNumber}&pageSize=${pageSize}&sortBy=categoryId&sortDir=asc`)
  }
  public getPost(pageNumber: number,pageSize:number){
    return this.http.get(`${baseUrl}/api/getAllPost?pageNumber=${pageNumber}&pageSize=${pageSize}&sortBy=postId&sortDir=asc`)
  }
  public getPostByCategory(cid:any){
    return this.http.get(`${baseUrl}/api/getPostByCategory/${cid}`)
  }
  public deleteCategory(categoryId:any){
    return this.http.delete(`${baseUrl}/api/categories/deleteCategory/${categoryId}`)
  }

  public createComment(data:any,postId:any){
    return this.http.post(`${baseUrl}/api/createComment/${postId}`,data);
  }
  public getCommnetByPost(postId:any){
    return this.http.get(`${baseUrl}/api/getCommentByPost/${postId}`)
  }
}
