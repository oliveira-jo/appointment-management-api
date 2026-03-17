export interface AppointmentResponse {
  id: string
  customerName: string
  professionalName: string
  productName: string
  scheduledAt: string
  status: string
}

export interface AppointmentRequest {
  customerEmail: string
  professionalEmail: string
  productName: string
  scheduledAt: string
}

export interface Metrics {
  todayAppointments: number
  weekAppointments: number
  todayRevenue: number
  totalAppointments: number
}

export interface Page<T> {
  content: T[]
  totalElements: number
  totalPages: number
  size: number
  number: number
  last: boolean
}