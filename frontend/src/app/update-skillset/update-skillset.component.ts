import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
 
@Component({
  selector: 'app-update-skillset',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './update-skillset.component.html',
  styleUrls: ['./update-skillset.component.css']
})
export class UpdateSkillsetComponent {
  allSkills: string[] = ['Java', 'CPP', 'Python', 'UI/UX Design' ,'JavaScript', 'PHP', 'SQL', 'SpringBoot', 'Django', 'MEAN', 'MERN', 'Object Oriented Design','System Design', 'Microservices','Service Oriented Architecture', 'Business Process Management',  'Manual Testing', 'Automation testing', 'Cloud Computing', 'AWS', 'Docker', 'DevOps', 'SDLC', 'Agile'];
  selectedSkills: string[] = [];
  customSkill: string = '';
  updateMessage: string = '';
 
  constructor(private http: HttpClient) {}
 
  ngOnInit(): void {
    const empId = localStorage.getItem('empId');
    if (empId) {
this.http.get<string>(`http://localhost:8080/getSkillSetForEmployee/${empId}`,{ responseType: 'text' as 'json'}).subscribe(
   (response: string) => {
    if(response && response.trim().length>0){
      this.selectedSkills = response.split(',').map(skill => skill.trim()).filter(skill => skill !== '');

    } else {
      this.selectedSkills = [];
    } 
        },
        (error) => {
          console.error('Error fetching skillset', error);
        }
      );
    }
  }
 
  onCheckboxChange(skill: string, event: Event): void {
    const isChecked = (event.target as HTMLInputElement).checked;
    if (isChecked && !this.selectedSkills.includes(skill)) {
      this.selectedSkills.push(skill);
    } else if (!isChecked) {
      this.selectedSkills = this.selectedSkills.filter(s => s !== skill);
    }
  }
 
  updateSkills(): void {
    const empId = localStorage.getItem('empId');
    if (!empId) {
      console.error('Employee ID not found');
      return;
    }
 
    if(this.customSkill && this.customSkill.trim() !== ''){
      this.selectedSkills.push(this.customSkill.trim());
    }
    const body = {
      skillSet: this.selectedSkills.join(',')
    };
 
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
 
    this.http.put(`http://localhost:8080/updateSkillSet/${empId}`, body, { headers, responseType: 'text' }).subscribe({
      next: (response: string)  => {
        console.log('Update resonse:', response);
        if(response==="Skill set updated successfully"){
          this.updateMessage = "Skill set updated successfully"
        } else {
          this.updateMessage= "Failed to update";
        }
      },
      error: err => {
        console.error('Failed to update skill set', err);
        alert('Failed to update skill set');
        this.updateMessage = 'Failed to update skills.';
      }
    });
  }
}