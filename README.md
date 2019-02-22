Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

Prerequisites
What things you need to install the software and how to install them:

your favourite IDE
Angular 6+ for the frontend to work properly
Spring Boot for the backend -> add environment variables as shown in application.properties (linked below)
node_modules and e2e libraries added to frontend package (Angular generates these at a new project start)
also run npm install to load all packages used in the project

spring.datasource.url=jdbc:postgresql://localhost:5432/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false
Running the project

Go to the backend/src/main/java/com.codecool.kulcssofttest package and run KulcssofttestApplication.java

Go to the frontend module and run ng serve

Go to localhost:4200

Built With
Angular 6 - The web framework used
Maven - Dependency Management
Spring - Server side framework
Security and Publicity
The page uses Spring Security to handle users correctly, since this is a REST application. Json Web Token is sent to the user back when logged in to be able to recognize the exact user when he requests some resource from the server later.
