import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router, RouterLink } from '@angular/router';
 
@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
 
  user = {
    name: '',
    username: '',
    password: '',
    role: '',
    designation: '',
    availability: '',
    projectId: 0,
    skills: {
      Java: false,
      CPP: false,
      Python: false,
      SpringBoot: false,
      SQL: false
    },
    skillSet: ''
  };
 
  skillValidationError = false;
 
  projectIds: number[] = []; 
 
  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
      this.fetchProjectIds();
  }

  fetchProjectIds(): void{
    this.http.get<number[]>('http://localhost:8080/getAllProjectIds').subscribe({
      next: (data)=> {
        this.projectIds = data;
      },
      error:(err) => {
        console.error("Error fetching project IDs:", err);
      }
    });
  }
 
  onSubmit() {
    const selectedSkills = Object.entries(this.user.skills)
      .filter(([, value]) => value)
      .map(([key]) => key);
 
    if (selectedSkills.length === 0) {
      this.skillValidationError = true;
      return;
    }
 
    this.skillValidationError = false;
 
    const payload = {
      name: this.user.name,
      username: this.user.username,
      password: this.user.password,
      role: this.user.role,
      designation: this.user.designation,
      availability: this.user.availability,
      projectId: this.user.projectId,
      skillSet: selectedSkills.join(',')
    };

    console.log('Signup payload', payload);
    
    this.http.post('http://localhost:8080/signup', payload, {responseType: 'text'}).subscribe({
      next: (response) => {
        console.log('Signup successful:', response);
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.error('Signup failed:', error);
      }
    });
  }
}