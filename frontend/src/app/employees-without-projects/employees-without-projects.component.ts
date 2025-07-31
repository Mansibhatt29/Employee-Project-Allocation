import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
 
@Component({
  selector: 'app-employees-without-projects',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './employees-without-projects.component.html',
  styleUrls: ['./employees-without-projects.component.css']
})
export class EmployeesWithoutProjectsComponent implements OnInit {
  employees: any[] = [];
  errorMessage: string = '';
 
  constructor(private http: HttpClient) {}
 
  ngOnInit(): void {
    this.fetchEmployeesWithoutProjects();
  }
 
  fetchEmployeesWithoutProjects(): void {
  this.http.get<any[]>('http://localhost:8080/getEmployeesWithoutProject').subscribe({
      next: (data) => {
        this.employees = data;
      },
      error: (err) => {
        console.error('Error fetching employees without projects:', err);
        this.errorMessage = 'Failed to load data.';
      }
    });
  }
}