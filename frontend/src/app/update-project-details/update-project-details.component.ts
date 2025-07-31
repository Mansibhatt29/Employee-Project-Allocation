import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
 
@Component({
  selector: 'app-update-project-details',
  templateUrl: './update-project-details.component.html',
  styleUrls: ['./update-project-details.component.css'],
  standalone: true,
  imports: [FormsModule, CommonModule]
})
export class UpdateProjectDetailsComponent implements OnInit {
  projectId !: number;
  projectName: string = '';
  descript: string = '';
  capacity: number | null = null;
  availableSkills: string[] = ['Java', 'CPP', 'Python', 'UI/UX Design' ,'JavaScript', 'PHP', 'SQL', 'SpringBoot', 'Django', 'MEAN', 'MERN', 'Object Oriented Design','System Design', 'Microservices','Service Oriented Architecture', 'Business Process Management',  'Manual Testing', 'Automation testing', 'Cloud Computing', 'AWS', 'Docker', 'DevOps', 'SDLC', 'Agile'];
  selectedSkills: string[] = [];
  customSkill: string = '';
  successMessage: string = '';
  errorMessage: string = '';
 
  constructor(private http: HttpClient, private route: ActivatedRoute) {}
 
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.projectId = +params['id'];
      console.log('Project ID from URL:', this.projectId);
      if(this.projectId){
        this.fetchProjectDetails();
      } else {
        this.errorMessage = 'No message ID';
      }
    }); 
  }
 
  fetchProjectDetails(): void {
this.http.get<any>(`http://localhost:8080/getProjectById/${this.projectId}`).subscribe({
      next: (data) => {
        this.projectName = data.projectName;
        this.descript = data.descript;
        this.capacity = data.capacity;
        this.selectedSkills = data.requiredSkills ? data.requiredSkills.split(',') : [];
      },
      error: () => {
        this.errorMessage = 'Failed to fetch project details.';
      }
    });
  }
 
  onSkillChange(skill: string, event: any): void {
    if (event.target.checked) {
      this.selectedSkills.push(skill);
    } else {
      this.selectedSkills = this.selectedSkills.filter(s => s !== skill);
    }
  }
 
  updateProject(): void {
    
    if(this.customSkill && this.customSkill.trim() !== ''){
      this.selectedSkills.push(this.customSkill.trim());
    }
    
    const updatedProject = {
      projectName: this.projectName,
      descript: this.descript,
      capacity: this.capacity,
      requiredSkills: this.selectedSkills.join(',')
    };


 
    this.http.put(`http://localhost:8080/updateProjectDetails/${this.projectId}`, updatedProject, {responseType: 'text'})
    .subscribe({
      next: (response: any) => {
        this.errorMessage = '';
        this.successMessage = 'Project updated successfully!';
      },
      error: () => {

        console.error('Update failed');
        this.errorMessage = 'Failed to update project.';
        this.successMessage = '';
      }
    });
  } 
}