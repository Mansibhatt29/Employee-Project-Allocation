import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
 
interface Project {
  projectId: number;
  projectName: string;
  capacity: number;
  requiredSkills: string;
}
 
@Component({
  selector: 'app-understaffed-projects',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './understaffed-projects.component.html',
  styleUrls: ['./understaffed-projects.component.css']
})
export class UnderstaffedProjectsComponent implements OnInit {
  projects: Project[] = [];
  errorMessage: string = '';
 
  constructor(private http: HttpClient) {}
 
  ngOnInit(): void {
    this.fetchUnderstaffedProjects();
  }
 
  fetchUnderstaffedProjects(): void {
    this.http.get<Project[]>('http://localhost:8080/getUnderStaffedProjects')
      .subscribe({
        next: (data) => {
          this.projects = data;
        },
        error: (err) => {
          console.error('Error fetching understaffed projects:', err);
          this.errorMessage = 'Failed to load projects.';
        }
      });
  }
}
 