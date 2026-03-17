import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppointmentRequest, AppointmentResponse, Metrics, Page } from './appointment-model';

declare var bootstrap: any;

@Injectable({ providedIn: 'root' })
export class AppointmentService {

  private apiUrl = 'http://localhost:8080/api/v1/appointments';

  constructor(private http: HttpClient) { }

  getAll(page: number = 0, size: number = 20): Observable<Page<AppointmentResponse>> {
    return this.http.get<Page<AppointmentResponse>>(
      `${this.apiUrl}?page=${page}&size=${size}`
    )
  }

  getMetrics() {
    return this.http.get<Metrics>(`${this.apiUrl}/metrics`);
  }

  getByDay(date: string) {
    return this.http.get<any[]>(`${this.apiUrl}/day/${date}`);
  }

  create(data: AppointmentRequest) {
    return this.http.post(this.apiUrl, data);
  }

  delete(id: string) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

}




