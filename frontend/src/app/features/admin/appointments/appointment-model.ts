export interface Appointment {
  id: string
  customerName: string
  professionalName: string
  productName: string
  scheduledAt: string
  status: string
}

export interface Page<T> {
  content: T[]
  totalElements: number
  totalPages: number
  size: number
  number: number
  last: boolean
}