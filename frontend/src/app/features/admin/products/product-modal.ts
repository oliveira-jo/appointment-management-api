export interface ProductResponse {
  id?: string;
  name: string;
  durationInSeconds: number;
  price: number;
}

export interface ProductRequest {
  name: string;
  durationInSeconds: number;
  price: number;
}