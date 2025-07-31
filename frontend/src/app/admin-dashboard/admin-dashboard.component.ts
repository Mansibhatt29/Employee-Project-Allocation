import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClient} from '@angular/common/http';
 
@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit{

  constructor(private http: HttpClient, private router: Router) {}

  empName: string='';
  pendingCount: number = 0;
  ngOnInit(): void{
    this.empName = localStorage.getItem('empName') || 'User';
    console.log('Welcome: ', this.empName);

    this.http.get<number>('http://localhost:8080/getPendingRequestCount').subscribe({
      next: (count) => this.pendingCount = count,
      error: () => this.pendingCount = 0
    });
  }

  goToRequests(){
    this.router.navigate(['/manage-role-requests']);
  }

  logout() {
    this.router.navigate(['/']); // Redirect to login
  }


}

