import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
 
@Component({
  selector: 'app-search-employees-by-designation',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './search-employees-by-designation.component.html',
  styleUrls: ['./search-employees-by-designation.component.css']
})
export class SearchEmployeesByDesignationComponent {
  designation: string = '';
  employees: any[] = [];
  errorMessage: string = '';
 
  constructor(private http: HttpClient) {}
 
  searchByDesignation() {
    if (!this.designation.trim()) {
      this.errorMessage = 'Please enter a designation';
      this.employees = [];
      return;
    }
 
this.http.get<any[]>(`http://localhost:8080/getAllEmployees/${this.designation}`).subscribe({
      next: (data) => {
        this.employees = data;
        this.errorMessage = '';
      },
      error: (error) => {
        this.errorMessage = 'Error fetching employees';
        this.employees = [];
        console.error(error);
      }
    });
  }
}