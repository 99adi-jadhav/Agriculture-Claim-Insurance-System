# Agriculture Claim Insurance System

## Overview
The **Agriculture Claim Insurance System** is designed to facilitate farmers and insurers in managing insurance claims efficiently.
The system allows users to submit, process, and track claims seamlessly through a web-based interface.

## Project Structure

/agriculture-claim-insurance-system
│-- /frontend        # Contains HTML, CSS, and JavaScript files
│-- /backend         # Contains Node.js (Express.js) server-side code
│-- /database        # MongoDB for database setup

## Technologies Used
- Frontend: HTML, CSS, JavaScript (without React)
- Backend: Node.js with Express.js
- Database: MongoDB (MongoDB Compass for GUI management)

## Features
- Users can submit insurance claims online.
- Secure authentication for users and administrators.
- MongoDB used for data storage and management.

## Prerequisites
Ensure you have the following installed before setting up the project:

### Development Tools:
- VS Code (for code editing)
- MongoDB Compass (for database management)

### Installation Requirements:
- Node.js (for running the backend server)
- MongoDB (for database setup)
- MongoDB Compass (optional, for GUI-based database management)
- Express.js (for building the backend API)

## Setting Up the Database
1. Open MongoDB Compass or use the terminal.
2. Create a new database named `agriculture_claim_insurance_system`.
3. Ensure MongoDB is running and accessible.

## Running the Application
### Backend Setup
1. Navigate to the backend folder:
   
   cd backend/
   
2. Install dependencies:
   
   npm install
   
3. Configure your .env file with MongoDB connection details.
4. Start the backend server:
   
   node server.js
   

### Frontend Setup
1. Navigate to the frontend folder:
   
   cd frontend/
   
2. Start a local server using:
   
   npx http-server
   

### Database Setup
1. Open the terminal and run:
   mongosh
   
   OR
   
2. Open MongoDB Compass and establish a connection.


## References
- ChatGPT AI Tool
- Youtube Video : https://youtu.be/ItRXMRnUmNE?si=vsDa36HR_Bmm8VJE
