export interface CustomerResponse {
  id?: string;
  name: string;
  phone: string;
  email: string;
  role: string;
}

export interface CustomerRequest {
  name: string;
  email: string;
  phone: string;
}