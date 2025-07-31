import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
 
export interface RoleRequest{
  id: number;
  designation: string;
  status:string;
  managerEmpId: number;
  selectedEmployeeId?: number;
  selectedEmployeeName?: string;
  showAssigned?: boolean;
}
@Component({
  selector: 'app-manager-request-status',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './manager-request-status.component.html',
  styleUrls: ['./manager-request-status.component.css']
})
export class ManagerRequestStatusComponent implements OnInit {
  roleRequests: any[] = [];
  managerEmpId: string |null = null;
  isLoading: boolean = true;
  errorMessage: string = '';
 
  constructor(private http: HttpClient) {}

  toggleAssigned(req: any){
    req.showAssigned =! req.showAssigned
  }
 
  ngOnInit(): void {
    this.managerEmpId = localStorage.getItem('empId');
    if (this.managerEmpId) {
      this.fetchManagerRequests();
    } else {
      this.errorMessage = 'Manager not logged in.';
      this.isLoading = false;
    }
  }
 
  fetchManagerRequests(): void {
    this.http.get<any>(`http://localhost:8080/getRequestsByManager?managerEmpId=${this.managerEmpId}`)
      .subscribe({
        next: (data) => {
          this.roleRequests = data;
          this.isLoading = false;
        },
        error: () => {
          this.errorMessage = 'Error fetching role requests.';
          this.isLoading = false;
        }
      });
  }
}
 