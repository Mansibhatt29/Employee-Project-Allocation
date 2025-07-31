import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
 
interface ProjectDTO {
  projectId: number;
  projectName: string;
  descript: string;
  requiredSkills: string;
  capacity: number;
  matchPercentage: number;
}
 
@Component({
  selector: 'app-get-matching-projects',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './get-matching-projects.component.html',
  styleUrls: ['./get-matching-projects.component.css']
})
export class GetMatchingProjectsComponent implements OnInit {
  empId: number = 0;
  matchedProjects: ProjectDTO[] = [];
  errorMessage: string = '';
  successMessage: string = '';
  resultChecked: boolean = false;
  employeeDetails: any = null;
 
  constructor(private http: HttpClient) {}
 
  ngOnInit(): void {}
 
  fetchMatchingProjects(): void {
    if (!this.empId) {
      this.successMessage = '';
      this.errorMessage = 'Please enter a valid Employee ID';
      this.resultChecked= false;
      return;
    }
 
  this.http.get<ProjectDTO[]>(`http://localhost:8080/getMatchingProjectsForEmployee/${this.empId}`).subscribe({
      next: (data) => {
        this.matchedProjects = data;
        this.resultChecked = true;
        this.successMessage= 'Matching projects retrieved successfully';
        this.errorMessage = '';
        if(data.length === 0){
          this.successMessage = '';
          this.errorMessage = 'No successful matches found.';
        }
      },
      error: (err) => {
        console.error(err);
        this.errorMessage = 'Error fetching matching projects';
        this.resultChecked = false;
      }
    });
  }
}