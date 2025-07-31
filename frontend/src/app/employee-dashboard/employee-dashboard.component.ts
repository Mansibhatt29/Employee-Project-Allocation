import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { RouterLink } from '@angular/router';
 
@Component({
  selector: 'app-employee-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './employee-dashboard.component.html',
  styleUrls: ['./employee-dashboard.component.css']
})
export class EmployeeDashboardComponent implements OnInit{
 
  constructor(private router: Router) {}

  empName: string='';
  ngOnInit(): void{
    this.empName = localStorage.getItem('empName') || 'User';
    console.log('Welcome: ', this.empName);
  }

 
  viewDetails() {
    this.router.navigate(['/view-employee-details']);
  }
 
  viewAssignedProject() {
    this.router.navigate(['/view-assigned-project']);
  }
 
  viewProjects() {
    this.router.navigate(['/view-projects']);
  }

  updateSkillset() {
    this.router.navigate(['/update-skillset']);
  }
 
  logout() {
    this.router.navigate(['/']); // Redirect to login
  }
}