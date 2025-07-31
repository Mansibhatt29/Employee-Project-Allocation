import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
 
interface Project {
  projectId: number;
  projectName: string;
  capacity: number;
}
 
@Component({
  selector: 'app-update-employee',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {
 
  employee: any = {
    empId: 0,
    empName: '',
    designation: '',
    availability: true,
    skillSet: '',
    projectEntity: {
      projectId: null,
    }
  };
 
  errorMessage: string = '';
  successMessage: string = '';
  message: string = ''; // ✅ Fix for undefined `message`
  projectList: Project[] = [];
 
  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router
  ) {}
 
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const empId = Number(params['id']);
      if (empId) {
        this.fetchEmployeeDetails(empId);
      }
    });
    this.fetchAllProjects();
  }
 
  fetchEmployeeDetails(empId: number) {
    
    this.http.get<any>(`http://localhost:8080/getEmployeeById/${empId}`).subscribe({
      next: (data) => {
        if (!data.projectEntity){
          data.projectEntity = {projectId: null};
        }
        this.employee = data;
        this.errorMessage = '';
        console.log('fetched employee:', this.employee);
      },
      error: () => {
        this.errorMessage = 'Failed to fetch employee details';
      }
    });
  }
 
  fetchAllProjects() {
  this.http.get<Project[]>('http://localhost:8080/getAllProjects').subscribe({
      next: (projects) => {
        this.projectList = projects;
      },
      error: () => {
        console.error('failed to fetch projects');
      }
    });
  }
 
  onUpdate(): void {
  this.http.put(`http://localhost:8080/updateEmployeeDetails/${this.employee.empId}`, this.employee, { responseType: 'text' }).subscribe({
      next: (response) => {
        console.log('Update response:', response);
        console.log('submitting employee update:', this.employee);
        this.message = 'Employee updated successfully!';
        this.successMessage = this.message;
        this.errorMessage = '';
      },
      error: (err) => {
        console.error('Update failed:', err);
        this.message = 'Update failed. Please try again.';
        this.errorMessage = this.message;
        this.successMessage = '';
      }
    });
  }
}