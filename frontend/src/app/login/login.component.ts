import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

 

interface LoginResponse{
  message: string;
  role: string;
  empName: string;
}

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  // loginData = {
  //   username: '',
  //   password: ''
  // };
 
  username: string = '';
  password: string = '';
  errorMessage: string = '';
  successMessage: string = '';
 
  constructor(private http: HttpClient, private router: Router) {}
 
  onLogin() {
    const loginData = { username: this.username, password: this.password};
    this.http.post<any>('http://localhost:8080/login', loginData)
      .subscribe({
        next: (response) => {
          localStorage.setItem('empName', response.empName);
          localStorage.setItem('empId', response.empId);
          localStorage.setItem('role', response.role);

          if((response.role === 'employee'|| response.role === 'manager') && loginData.password === 'temp123'){
            alert('You are logging in for the first time. Please update your password');
            this.router.navigate(['/update-password']);
          } else if (response.role === 'employee') {
            this.router.navigate(['/employee-dashboard']);
          } else if (response.role === 'admin') {
            this.router.navigate(['/admin-dashboard']);
          } else if (response.role === 'manager') {
            this.router.navigate(['/manager-dashboard']);
          } else {
            this.errorMessage = 'Unknown role!';
          }
          console.log('Login successful:', response);
          localStorage.setItem('loggedInUsername', this.username);


        },
        error: (error) => {
          console.error('Login failed:', error);
          this.errorMessage = 'Invalid username or password';
        }
      });
  }
}