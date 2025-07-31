import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
 
export interface Project{
  projectId: number;
}

@Component({
  selector: 'app-add-employee',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})

export class AddEmployeeComponent {
  employee = {
    empName: '',
    designation: '',
    availability: true,
    projectEntity : {projectId: 0},
    skillSet: '',
    email: '',
    role: ''
    // username: '',
    // password:''
  };
 
  skills: string[] =['Java', 'CPP', 'Python', 'UI/UX Design' ,'JavaScript', 'PHP', 'SQL', 'SpringBoot', 'Django', 'MEAN', 'MERN', 'Object Oriented Design','System Design', 'Microservices','Service Oriented Architecture', 'Business Process Management',  'Manual Testing', 'Automation testing', 'Cloud Computing', 'AWS', 'Docker', 'DevOps', 'SDLC', 'Agile'];
  selectedSkills: string[] =[];
  customSkill: string='';
  projectIds: (number | null)[] = [];
  successMessage: string = '';
  errorMessage: string = '';
 
  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchProjectIds();
  }
 
  fetchProjectIds() {
  this.http.get<Project[]>('http://localhost:8080/getAllProjects').subscribe({
      next: (data) => {
        this.projectIds = data.map((project: Project)=> project.projectId);
        this.projectIds.unshift(null);
      },
      error: (error) => {
        console.error('Error fetching projects:', error);
      }
    });
  }
 
  toggleSkill(skill: string, event: any) {
    if (event.target.checked) {
      this.selectedSkills.push(skill);
    } else {
      const index = this.selectedSkills.indexOf(skill);
      if (index > -1) {
        this.selectedSkills.splice(index, 1);
      }
    }
    if(this.customSkill && this.customSkill.trim() !== ''){
      this.selectedSkills.push(this.customSkill.trim());
    }
    this.employee.skillSet = this.selectedSkills.join(', ');
  }
 
  addEmployee() {
    console.log('Adding employee:', this.employee);
    this.http.post('http://localhost:8080/addEmployee', this.employee, { responseType: 'text' })
      .subscribe({
        next: (response: string) => {
          const match = response.match(/(\d+)/);
          const empId = match? match[1]: 'Unknown';
          console.log('Employee added successfully:', response);
          this.successMessage = `Employee with ID ${empId} added successfully!`;
          this.errorMessage = '';
        },
        error: (error) => {
          console.error('Error adding employee:', error);
          this.errorMessage = 'Failed to add employee.';
          this.successMessage = '';
        }
      });
  }
}