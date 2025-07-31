import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Router, RouterLink } from '@angular/router';

 
@Component({
  selector: 'app-manage-role-requests',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './manage-role-requests.component.html',
  styleUrls: ['./manage-role-requests.component.css']
})
export class ManageRoleRequestsComponent implements OnInit {
 
  pendingRequests: any[] = [];
  loading = true;
  error = '';
  projectId: number =0;
  projectName: string = '';
 
  constructor(private route: ActivatedRoute, private http: HttpClient , private router: Router) {}
 
  ngOnInit(): void {
    this.fetchPendingRequests();

  }

  
 
  fetchPendingRequests(): void {
    this.http.get<any>('http://localhost:8080/getAllPendingRequests')
      .subscribe({
        next: (data) => {
          this.pendingRequests = data;
          this.loading = false;
        },
        error: (err) => {
          console.error(err);
          this.error = 'Unable to load pending requests.';
          this.loading = false;
        }
      });
  }
}