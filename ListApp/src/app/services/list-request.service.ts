import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders,HttpParams } from '@angular/common/http';

const httpHeaders = {
  headers: new HttpHeaders({'Content-type':'application/x-www-form-urlencoded'})
};

@Injectable({
  providedIn: 'root'
})
export class ListRequestService {

  constructor(private http: HttpClient) { }

  getLists(){
    return this.http.get("http://localhost:8080/search/all/lists");
  }

  getListById(id: number){
    return this.http.get("http://localhost:8080/search/list/" + id);
  }

  createList(name:string){
    const httpParams = new HttpParams().set('listName', name); 
    return this.http.post("http://localhost:8080/save/list",httpParams.toString(),httpHeaders);
  }

  addProductInList(nameProduct, idList){
    const httpParams = new HttpParams().set('productName', nameProduct); 
    return this.http.post("http://localhost:8080/save/product/list/" + idList ,httpParams, httpHeaders)
  }

  updateList(name:string, id: number){
    const httpParams = new HttpParams().set('listName', name);
    return this.http.put("http://localhost:8080/modify/list/" + id ,httpParams.toString(), httpHeaders);
  }

  deleteProductInList(idProduct:number, idList: number){ 
    return this.http.delete("http://localhost:8080/delete/" + idList + "/" + idProduct);
  }
}
