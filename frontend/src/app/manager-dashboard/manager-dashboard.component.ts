import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-manager-dashboard',
  imports: [CommonModule, RouterModule],
  templateUrl: './manager-dashboard.component.html',
  styleUrl: './manager-dashboard.component.css'
})
export class ManagerDashboardComponent implements OnInit {

  constructor(private router: Router) {}

  empName: string='';
  ngOnInit(): void{
    this.empName = localStorage.getItem('empName') || 'User';
    console.log('Welcome: ', this.empName);
  }

  viewAssignedProject() {
    this.router.navigate(['/view-assigned-project']);
  }

  updateSkillset() {
    this.router.navigate(['/update-skillset']);
  }

  goToRaiseRequest(){
    this.router.navigate(['/raise-employee-request']);
  }
 
  goToRequestStatus(){
    this.router.navigate(['/manager-request-status']);
  }
  logout() {
    this.router.navigate(['/']); // Redirect to login
  }


}
