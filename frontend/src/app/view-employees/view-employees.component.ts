import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Router} from '@angular/router';
 

export interface Project{
  projectId: number;
  projectName: string;
  capacity: number;
}
export interface Employee {
  empId: number;
  empName: string;
  designation: string;
  availability: boolean;
  projectEntity : Project | null;
  skillSet: string;
}
@Component({
  selector: 'app-view-employees',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './view-employees.component.html',
  styleUrls: ['./view-employees.component.css']
})
export class ViewEmployeesComponent implements OnInit {
 
  employees: Employee[] = [];  // array to hold employee objects
  errorMessage: string = '';
  successMessage: string = '';
 
  constructor(private http: HttpClient, private router: Router) {}
 

  ngOnInit(): void {
    this.fetchEmployees();
  }
 
  fetchEmployees(): void{
    this.http.get<Employee[]>('http://localhost:8080/getAllEmployees')
      .subscribe({
        next: (data) => {
          this.employees = data;
        },
        error: (err) => {
          console.error('Error fetching employees:', err);
          this.errorMessage = 'Failed to load employee data.';
        }
      });
  }

  goToUpdate(empId: number): void{
    this.router.navigate(['/update-employee'], { queryParams: { id: empId}} );
  }

  deleteEmployee(empId: number):void{
    const confirmed = confirm('Are you sure you want to delete this employee?');
    if(!confirmed) return;

    this.http.delete(`http://localhost:8080/deleteEmployeeById/${empId}`,
      { responseType: 'text' }).subscribe({
        next: (response) => {
          this.successMessage = response;
          this.errorMessage = '';
          this.fetchEmployees();
        },
        error: (err) => {
          console.error('Error deleting employee', err);
          this.errorMessage = 'Failed to delete employee.';
          this.successMessage = '';
          
        }
      })
    
  }







}