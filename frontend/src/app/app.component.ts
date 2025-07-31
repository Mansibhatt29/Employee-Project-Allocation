import { Component } from '@angular/core';
import { LoginComponent } from "./login/login.component";
import { SignupComponent } from "./signup/signup.component";
import { RouterOutlet } from '@angular/router';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  constructor(private titleService: Title) {
    this.titleService.setTitle('Employee Management');
  }
  ngOnInit(): void {
 
  }
 
  //title = 'EmployeeManagement';
}
