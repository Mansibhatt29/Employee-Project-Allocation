import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { last } from 'rxjs';
 

interface Employee{
  empId: number;
  empName: string;
  designation: string;
}
@Component({
  selector: 'app-view-assigned-project',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './view-assigned-project.component.html',
  styleUrls: ['./view-assigned-project.component.css']
})
export class ViewAssignedProjectComponent implements OnInit {
  project: any = null;
  projectMembers: Employee[] = [];
  errorMessage: string = '';
  showMembers: boolean = false;
 
  constructor(private http: HttpClient) {}

  ngOnInit() {
      const savedEmpId = localStorage.getItem('empId');
      if(savedEmpId){
        const empId = Number(savedEmpId);
        this.fetchAssignedProject(empId);
      } else {
        this.errorMessage = 'Employee  not found in local storage';
      }
  }
 
  fetchAssignedProject(empId: number): void {
    this.http.get(`http://localhost:8080/getProjectByEmployeeId/${empId}`)
      .subscribe({
        next: (data) => {
          if(data){
            this.project = data;
            this.errorMessage = '';
            this.fetchProjectMembers(this.project.projectId);

          } else {
            this.errorMessage = 'No project assigned to this employee';
          }
        },
        error: (error) => {
          this.errorMessage = 'Error fetching project details';
          this.project = null;
        }
      });
  }

  fetchProjectMembers(projectId: number): void {
this.http.get<Employee[]>(`http://localhost:8080/getEmployeesInProject/${projectId}`)
    .subscribe({
      next: (data) => {
        this.projectMembers = data;
      },
      error: (error) => {
        console.error('Error fetching project members:', error);
        this.projectMembers = [];
      }
    });
  }

  toggleMembers(): void{
    this.showMembers = !this.showMembers;
  }
}
