import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
 
@Component({
  selector: 'app-add-project',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.css']
})
export class AddProjectComponent implements OnInit {
 
  project = {
    projectName: '',
    descript: '',
    capacity: 0,
    requiredSkills: ''
  };
 
  availableSkills: string[] = ['Java', 'CPP', 'Python', 'UI/UX Design' ,'JavaScript', 'PHP', 'SQL', 'SpringBoot', 'Django', 'MEAN', 'MERN', 'Object Oriented Design','System Design', 'Microservices','Service Oriented Architecture', 'Business Process Management',  'Manual Testing', 'Automation testing', 'Cloud Computing', 'AWS', 'Docker', 'DevOps', 'SDLC', 'Agile'];
  selectedSkills: string[] = [];
 
  successMessage: string = '';
  errorMessage: string = '';
 
  constructor(private http: HttpClient) { }
 
  ngOnInit(): void { }
 
  // Handling skill selection safely
  onSkillChange(skill: string, event: Event) {
    const isChecked = (event.target as HTMLInputElement)?.checked;
 
    if (isChecked) {
      this.selectedSkills.push(skill);
    } else {
      const index = this.selectedSkills.indexOf(skill);
      if (index >= 0) {
        this.selectedSkills.splice(index, 1);
      }
    }
  }
 
  addProject() {
    // Convert selected skills array to comma-separated string
    this.project.requiredSkills = this.selectedSkills.join(',');
 
this.http.post('http://localhost:8080/addProject', this.project, { responseType: 'text' })
      .subscribe({
        next: (response) => {
          console.log('Project added successfully:', response);
          this.successMessage = 'Project added successfully!';
          this.errorMessage = '';
          // Reset form
          this.project = {
            projectName: '',
            descript: '',
            capacity: 0,
            requiredSkills: ''
          };
          this.selectedSkills = [];
        },
        error: (error) => {
          console.error('Error adding project:', error);
          this.errorMessage = 'Failed to add project.';
          this.successMessage = '';
        }
      });
  }
}