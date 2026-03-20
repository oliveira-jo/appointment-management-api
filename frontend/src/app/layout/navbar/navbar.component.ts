import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../core/auth/auth.service';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  isDarkMode: boolean = false;
  email: string | null = "";

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    this.email = this.authService.getEmail();
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  toggleDarkMode() {
    this.isDarkMode = !this.isDarkMode;
    document.body.classList.toggle('dark-mode', this.isDarkMode);
  }

}