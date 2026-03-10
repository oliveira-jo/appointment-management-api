import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomerRequest, CustomerResponse } from './customer-modal';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private API = "http://localhost:8080/api/v1/customers";

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get(this.API);
  }

  getById(id: string) {
    return this.http.get<CustomerResponse>(`${this.API}/${id}`);
  }

  getByEmail(email: string) {
    return this.http.get<CustomerResponse>(`${this.API}/email/${email}`);
  }

  create(data: CustomerRequest) {
    return this.http.post(this.API, data);
  }

  update(id: string, data: CustomerRequest) {
    return this.http.put(`${this.API}/${id}`, data);
  }

  delete(id: string) {
    return this.http.delete(`${this.API}/${id}`);
  }

}