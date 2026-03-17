import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AppointmentService } from '../appointments/appointment.service';
import { Metrics } from '../appointments/appointment-model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  imports: [RouterModule, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

  metrics: Metrics = { todayAppointments: 0, weekAppointments: 0, todayRevenue: 0, totalAppointments: 0 }

  constructor(private appointmentService: AppointmentService) { }

  ngOnInit() {
    this.loadMetrics();
  }

  loadMetrics() {
    this.appointmentService
      .getMetrics()
      .subscribe(metrics => {
        this.metrics = metrics;
      })
  }

}
