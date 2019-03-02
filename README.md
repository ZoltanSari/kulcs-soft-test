If you would like to check the project before build it, you can do it here:

https://kulcs-soft-assignment.herokuapp.com/

Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

Prerequisites
What things you need to install the software and how to install them:

Please download and install the free IntelliJ from this link: https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows

Please download and install the Java Enterpise Edition SDK 8 from this link: http://download.oracle.com/otn-pub/java/java_ee_sdk/8/java_ee_sdk-8.zip

Please download and install the PostgreSQL from this link: https://www.postgresql.org/download/windows/

Please download and install the Postgres Enterprise Manager for PostgreSQL from this link: https://www.enterprisedb.com/advanced-downloads#EnterpriseManager

Please create a database in Postgres Enterprise Manager (name and password are up to you)

Create new project in IntelliJ from Version Control -> git (clone the repository)

Spring Boot for the backend -> add environment variables as shown in application.properties (linked below)
(Run -> Edit configuration -> Environment -> Environment Variables)


spring.datasource.url=jdbc:postgresql://localhost:5432/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false

Node_modules and e2e libraries added to frontend package (Angular generates these at a new project start)
also run npm install to load all packages used in the project

Running the project

Go to the kulcssoftest/src/main/java/com.sari.kulcssofttest package and run KulcssofttestApplication.java

Go to the frontend module and run ng serve
(if you want to use the local app instead of heroku, change the baseUrl in 
frontend/src/app/services/user.service.ts)

Go to localhost:4200

Built With
Angular 6 - The web framework used
Maven - Dependency Management
Spring - Server side framework
Security and Publicity

The page uses Spring Security to handle users correctly, since this is a REST application. 
Json Web Token is sent to the user back when logged in to be able to recognize the exact user 
when he requests some resource from the server later.
