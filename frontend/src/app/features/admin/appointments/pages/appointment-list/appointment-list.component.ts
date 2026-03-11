import { Component, OnInit } from '@angular/core';
import { AppointmentService } from '../../appointment.service';
import { AppointmentRequest, AppointmentResponse, MetricsResponse, Page } from '../../appointment-model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';

declare var bootstrap: any;

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  selector: 'app-appointment-list',
  templateUrl: './appointment-list.component.html'
})
export class AppointmentListComponent implements OnInit {

  appointments: AppointmentResponse[] = [];
  appointment: AppointmentRequest = { customerEmail: '', professionalEmail: '', productName: '', scheduledAt: '' }

  page?: Page<AppointmentResponse>;

  currentPage = 0
  pageSize = 20

  searchProfessionalName = '';
  searchCustomerlName = '';
  toastMessage = '';

  selectedAppointmentId: string | null = null;

  // pagination
  pageNumber = 0;
  totalPages = 0;
  pages: number[] = [];

  // metrics cards
  metrics = {
    today: 0,
    week: 0,
    revenueToday: 0,
    total: 0
  };

  // filter
  filter = {
    startDate: '',
    endDate: ''
  };

  constructor(private appointmentService: AppointmentService, private router: Router) { }


  ngOnInit() {
    this.loadAppointments()
  }

  loadAppointments() {
    this.appointmentService
      .getAll(this.currentPage, this.pageSize)
      .subscribe(response => {
        this.page = response
        this.appointments = response.content
      })
  }

  nextPage() {
    if (!this.page?.last) {
      this.currentPage++
      this.loadAppointments()
    }
  }

  previousPage() {
    if (this.currentPage > 0) {
      this.currentPage--
      this.loadAppointments()
    }
  }

  search() {
    this.appointmentService.getByDay(this.filter.startDate)
      .subscribe(res => {
        this.appointments = res;
      });
  }

  delete() {
    if (!this.selectedAppointmentId) return;

    this.appointmentService.delete(this.selectedAppointmentId)
      .subscribe(() => {

        this.loadAppointments();
        this.loadMetrics();

        const modal = bootstrap.Modal.getInstance(
          document.getElementById('deleteModal')
        );

        modal.hide();

      });
  }

  openDeleteModal(id: string) {
    this.selectedAppointmentId = id;
    const modal = new bootstrap.Modal(
      document.getElementById('deleteModal')
    );
    modal.show();
  }

  clearFilter() {

    this.filter.startDate = '';
    this.filter.endDate = '';

    this.loadAppointments();
  }

  loadMetrics() {

    const metrics: MetricsResponse = { today: 1, week: 1, revenueToday: 0, total: 2 };
    this.metrics = metrics;

  }

}

