# College Lost and Found Management System

A Java Swing + MySQL + JDBC application for managing lost and found items in a college campus.

## Features
- Student registration and login
- Report lost items
- Report found items
- Search lost/found reports
- Claim found items
- My reports
- Admin dashboard
- Approve/reject claims
- Update item status
- Delete invalid reports

## Technology
- Java 17+
- Swing
- MySQL 8+
- JDBC
- Maven
- GitHub

## Setup
1. Install Java 17+, MySQL 8+, and Maven.
2. Open MySQL Workbench and run `database/college_lost_found.sql`.
3. Open `src/main/java/com/college/lostfound/database/DBConnection.java`.
4. Replace `YOUR_MYSQL_PASSWORD` with your local MySQL root password.
5. Open the project in IntelliJ IDEA, NetBeans, or Eclipse as a Maven project.
6. Run `com.college.lostfound.Main`.

## Default admin
Username: `admin`
Password: `admin123`

For an academic demo only. Production systems should use password hashing and proper authentication.

## Student flow
Register -> Login -> Dashboard -> Report/Search/Claim -> Logout.

## GitHub
Do not commit real passwords, database credentials, IDE files, or generated `target/` files.
