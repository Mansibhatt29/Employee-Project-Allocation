# 👨‍💼 Employee Project Allocation System
 
This is a full-stack web application that enables an organization to manage employees, projects, and skill-based allocations. Admins and Managers can allocate employees to projects based on skill matching and availability, while employees can manage their own skill sets and view their allocations.
 
---
 
## 🛠️ Tech Stack
 
| Layer      | Technology        |
|------------|-------------------|
| Frontend   | Angular (Standalone Components) |
| Backend    | Spring Boot (Java, REST APIs)  |
| Database   | MySQL             |
| Build Tool | Maven             |
| IDEs       | IntelliJ (backend), VS Code (frontend) |
 
---
 
## 📁 Project Structure
 
---
 
## 🌟 Features
 
### 👩‍💼 Admin Panel
- Add new employees with temporary passwords
- Add new projects and define required skill sets
- Approve or reject role upgrade requests raised by managers
- View and search pending role requests
- Assign employees to projects based on skill match and availability
 
### 👨‍💻 Manager Panel
- Raise role upgrade requests for employees
- View the status of all raised role requests
- View assigned employees
 
### 🙋 Employee Panel
- Login with credentials and update password on first login
- View personal details, assigned project(s), and availability status
- Update own skill set using checkboxes
- View matching projects based on skill match score
 
---
 
## 🔐 Authentication Flow
 
- Admin adds employee with a temporary password
- On first login, employee is forced to set a new password
- All role-based data access is controlled from backend
 
---
 
## 🚀 Getting Started
 
### ✅ 1. Clone the Repository
 
```bash
git clone https://github.com/your-username/employee-project-allocation.git
cd employee-project-allocation
 
