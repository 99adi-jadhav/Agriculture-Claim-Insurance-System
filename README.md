🌾 Agriculture Claim Insurance System
📌 Overview

The Agriculture Claim Insurance System is a web-based application designed to help farmers and insurance administrators manage crop insurance claims efficiently.
The platform enables users to submit claims, upload documents, track status, while admins can verify and approve those claims.

📁 Project Structure
/agriculture-claim-insurance-system
│-- /frontend        # HTML, CSS, JavaScript (No React)
│-- /backend         # Java Spring Boot backend
│-- /database        # SQL scripts & DB configuration

🛠 Technologies Used
Frontend

HTML5
CSS3
JavaScript (Vanilla JS)

Backend
Java 17+
Spring Boot
Maven
Database
MySQL

⭐ Key Features

✔ User registration and login
✔ Secure authentication for users & admins
✔ Claim submission with document upload
✔ Admin dashboard for claim approval/rejection
✔ Claim status tracking (Pending, Verified, Approved, Rejected)
✔ MySQL database integration

📌 Prerequisites
Software Requirements

VS Code / IntelliJ / Eclipse
MySQL Server & Workbench
Java 17 
Maven installed



🗄 Database Setup

Start MySQL server.
Create a new database:
CREATE DATABASE agriculture_insurance;


Update your Spring Boot application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/agriculture_insurance
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

🚀 How to Run the Application
🔹 Backend Setup (Spring Boot)

Open terminal and navigate to backend:

cd backend/


Build the project:

mvn clean install


Run the server:

java -jar target/insurance-backend-0.0.1-SNAPSHOT.jar

🔹 Frontend Setup

Open terminal and navigate to frontend:

cd frontend/


Start a local development server:
npx http-server

Open the provided URL in your browser (usually http://127.0.0.1:8080)



📚 References

ChatGPT AI Assistant

YouTube Tutorial: Agriculture Claim System
https:/youtu.be/ItRXMRnUmNE?si=vsDa36HR_Bmm8VJE
