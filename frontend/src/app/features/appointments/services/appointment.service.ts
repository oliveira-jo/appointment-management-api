import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Appointment {
  id: string;
  customerName: string;
  professionalName: string;
  date: string;
}

@Injectable({ providedIn: 'root' })
export class AppointmentService {

  private api = 'http://localhost:8080/api/v1/appointments';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(this.api);
  }
}