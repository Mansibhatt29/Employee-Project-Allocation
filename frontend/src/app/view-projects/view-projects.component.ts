import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Router} from '@angular/router';
 

export interface Project {
  projectId: number;
  projectName: string;
  descript: string;
  requiredSkills: string;
  capacity: number;
}
@Component({
  selector: 'app-view-projects',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './view-projects.component.html',
  styleUrls: ['./view-projects.component.css']
})
export class ViewProjectsComponent implements OnInit {
  projects: any[] = [];
  errorMessage: string = '';
 
  constructor(private http: HttpClient, private router: Router) {}
 
  ngOnInit(): void {
    this.fetchProjects();
  }
 
  fetchProjects(): void {
  this.http.get<Project[]>('http://localhost:8080/getAllProjects').subscribe({
      next: (data) => {
        this.projects = data;
      },
      error: (error) => {
        console.error('Error fetching projects:', error);
        this.errorMessage = 'Failed to load projects.';
      }
    });
  }

  goToUpdate(projectId: number): void{
    this.router.navigate(['/update-project-details'], { queryParams: { id: projectId}} );
  }

}
