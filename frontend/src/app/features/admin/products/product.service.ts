import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductRequest, ProductResponse } from './product-modal';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private API = "http://localhost:8080/api/v1/products";

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get(this.API);
  }

  getById(id: string) {
    return this.http.get<ProductResponse>(`${this.API}/${id}`);
  }

  getByName(name: string) {
    return this.http.get<ProductResponse[]>(`${this.API}/name/${name}`);
  }

  create(data: ProductRequest) {
    return this.http.post(this.API, data);
  }

  update(id: string, data: ProductRequest) {
    return this.http.put(`${this.API}/${id}`, data);
  }

  delete(id: string) {
    return this.http.delete(`${this.API}/${id}`);
  }

}