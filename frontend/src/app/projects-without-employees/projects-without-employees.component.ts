import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
 
interface Project {
  projectId: number;
  projectName: string;
  descript: string;
  requiredSkills: string;
  capacity: number;
}
 
@Component({
  selector: 'app-view-projects-without-employees',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './projects-without-employees.component.html',
  styleUrls: ['./projects-without-employees.component.css']
})
export class ProjectsWithoutEmployeesComponent implements OnInit {
 
  projects: Project[] = [];
  errorMessage: string = '';
 
  constructor(private http: HttpClient) {}
 
  ngOnInit(): void {
    this.fetchProjectsWithoutEmployees();
  }
 
  fetchProjectsWithoutEmployees(): void {
    this.http.get<Project[]>('http://localhost:8080/getProjectsWithoutEmployees').subscribe({
      next: (data) => {
        this.projects = data;
        this.errorMessage = '';
      },
      error: (err) => {
        console.error('Error fetching projects:', err);
        this.errorMessage = 'Failed to load projects.';
      }
    });
  }
}
 