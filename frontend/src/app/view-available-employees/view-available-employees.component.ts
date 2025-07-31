 
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Employee } from '../view-employees/view-employees.component';
 
@Component({
  selector: 'app-view-available-employees',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './view-available-employees.component.html',
  styleUrls: ['./view-available-employees.component.css']
})
export class ViewAvailableEmployeesComponent implements OnInit {
 
  employees: any[] = [];
  errorMessage: string = '';
 
  constructor(private http: HttpClient) {}
 
  ngOnInit(): void {
    this.fetchAvailableEmployees();
  }
 
  fetchAvailableEmployees() {
    this.http.get<Employee[]>('http://localhost:8080/getAllAvailableEmployees').subscribe({
      next: (data) => {
        this.employees = data;
        this.errorMessage = '';
      },
      error: (err) => {
        console.error('Error fetching available employees:', err);
        this.errorMessage = 'Failed to load available employees.';
      }
    });
  }
}
 