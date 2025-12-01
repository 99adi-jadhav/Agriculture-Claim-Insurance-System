# Agriculture Claim Insurance System

``` markdown
# Project Title:- Agriculture Claim Insurance System.

## Overview :- 
The Agriculture Claim Insurance System is a web-based platform developed using Java Spring Boot and MySQL.  
It allows farmers to submit insurance claims, upload supporting documents, and track claim status.  
Administrators can verify claims, approve/reject them, and manage all claim records efficiently.  

The system uses Spring Boot for backend processing and MySQL for persistent database storage.
```

## Technologies :

-   **Java 17+**
-   **Spring Boot**
-   **Maven**
-   **MySQL**
-   **HTML, CSS, JavaScript (Frontend)**
-   **RESTful API**

## Features :

-   User registration & login (Farmer + Admin)
-   Submit new claims with document upload
-   Track claim status (Pending → Verified → Approved/Rejected)
-   Admin dashboard for approving or rejecting claims
-   MySQL database integration
-   REST API for all claim operations

## Prerequisites :

### Development Tools:

-   **IDE :** VS Code / IntelliJ / Spring Tool Suite (STS)
-   **Postman :** for API testing

## Installation Requirements

### 1. Java 17 Installation

``` bash
sudo apt update
sudo apt install openjdk-17-jdk
java -version
```

### 2. Spring Boot Setup (STS Installation -- Optional)

#### Method 1 : Using .tar.gz Archive

1.  Download Spring Tool Suite from the official website.\
2.  Extract the file:

``` bash
tar -xvzf spring-tool-suite-*.tar.gz
```

3.  Move extracted folder:

``` bash
mv spring-tool-suite-* ~/apps/spring-tool-suite
```

4.  Launch STS:

``` bash
cd ~/apps/spring-tool-suite
./STS
```

### 3. Maven Installation

``` bash
sudo apt update
sudo apt install maven
```

Verify:

``` bash
mvn -version
```

### 4. MySQL Installation

``` bash
sudo apt update
sudo apt install mysql-server
```

Secure MySQL:

``` bash
sudo mysql_secure_installation
```

### 5. Postman Installation

``` bash
sudo snap install postman
```

# Setting Up the Database

### 1. Create database

``` sql
CREATE DATABASE agriculture_insurance;
```

``` sql
USE agriculture_insurance;
```

### 2. Create user and grant permission (Optional)

``` sql
CREATE USER 'root'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON agriculture_insurance.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Exit MySQL

``` sql
EXIT;
```

# Running the Application

## Backend Setup

Update application.properties:

``` properties
spring.datasource.url=jdbc:mysql://localhost:3306/agriculture_insurance
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.dbcp2.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

## Endpoints of the Project

-   POST → Submit a new claim\
-   GET → Retrieve claims\
-   PUT → Update claim / verify\
-   DELETE → Remove claim record

## Clone the repository:

``` bash
git clone https://github.com/99-adijadhav/agriculture-claim-insurance-system.git
```

## Build the backend using Maven

``` bash
mvn clean install
```

### Run JAR

``` bash
java -jar /path/to/insurance-backend.jar
```
