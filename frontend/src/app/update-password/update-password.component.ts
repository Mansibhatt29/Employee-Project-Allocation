import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
 
@Component({
  selector: 'app-update-password',
  imports: [CommonModule, FormsModule],
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']
})
export class UpdatePasswordComponent {
  username: string = '';
  newPassword: string = '';
  confirmPassword: string = '';
  errorMessage: string = '';
  successMessage: string = '';
 
  constructor(private http: HttpClient, private router: Router) {}
 
  ngOnInit() {
    const storedUsername = localStorage.getItem('loggedInUsername');
    if (storedUsername) {
      this.username = storedUsername;
    } else {
      this.errorMessage = 'User not found. Please login again.';
    }
  }
 
  updatePassword() {
    if (this.newPassword !== this.confirmPassword) {
      this.errorMessage = 'Passwords do not match.';
      return;
    }
 
    const requestBody = {
      username: this.username,
      newPassword: this.newPassword
    };
 
  this.http.put('http://localhost:8080/updatePassword', requestBody)
      .subscribe({
        next: (response: any) => {
          this.successMessage = 'Password updated successfully!';
          localStorage.removeItem('loggedInUsername');
          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 2000);
        },
        error: (error) => {
          if (error.error && typeof error.error === 'string') {
            this.errorMessage = 'error updating pass:' + error.error;
          } else{
            this.errorMessage = 'something went wrong ';
          }
        }
      });
  }
}