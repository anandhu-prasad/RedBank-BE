# RED BANK 
RedBank serves as a Typographyplatform to bridge the gap between the blood donors and recipients and to reduce the efforts required to find the right type of blood group and components. With RedBank , hospitals can easily view and manage their inventory, blood banks can sell blood to other users and any user can nake a request to all the active donors who are willing to donate their blood to save a life. With this App, the Hospitals and Blood Banks can organise a drive for collecting the blood for the needy people who might require blood in future.

## TABLE OF CONTENTS 

1. [General Info](#GeneralInfo) 
2. [Technologies](#Technologies) 
3. [Setup/Installation](#Setup/Installation)
4. [Backend Structure](#BackendStructure)
5. [Features](#Features)
6. [Contact](#Contact)


## General Info
We created this ***RED BANK*** to bridge the gap between the blood donors and recipients and to reduce the efforts required to find the right type of blood group. We did this in order to hassle the process of arranging blood and it’s components during emergencies without any difficulty in arranging right type of blood component in a shorter time period.
We have 4 roles in our project i.e: ***Buyers, Sellers, Donors, Viewers***

Similarly we have included 3 persona which are important to this project :
* Individuals(Users)
* Hospitals 
* Blood Banks  
      
## Technologies Used 
* React JS (For web application)
* React Native (For Mobile application)
* Java (Spring Boot)

## Setup/Installation
Blood Management System requires JAVA 15, Maven, React JS 17.0.1, React Native 0.63.4, npm 6.14, MySQL 8.0.23, Redux 4.0.5, Android Studio 11.0 to run.

###### DATABASE

Setup the database keeping a note of the Database name, Host, Port, Username and Password.
Import the >[db_dump.sql] file to create the databases tables required for the first run.  

###### BACKEND

The Directory javainuse/src/main/resources contains the application.properties config file for the backend. This includes the following configurations 
The port on which the server needs to be started 
> server.port=8080

###### Database connection credentials
`spring.datasource.url=jdbc:mysql://localhost/reddb?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false
spring.datasource.username=yourDatabaseUsername
spring.datasource.password=yourDatabasePassword
spring.datasource.platform=mysql
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57InnoDBDialect
spring.jpa.open-in-view=false
`
###### SMTP config for sending the mails from the Server 
`spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=redbank104@gmail.com
spring.mail.password=#Redbank123
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.connectiontimeout=5000
spring.mail.properties.mail.smtp.timeout=5000
spring.mail.properties.mail.smtp.writetimeout=5000
spring.mail.properties.
`
###### JWT configuration 
`jwt.secret=REDBANKo%q2ukuuk5kzn+_w@#z(uw@fc&83w+1%tq$!ooce783&(&l+8&`

By running the backend codes, this will start the Tomcat server in the system at port 8080. So, to access the server use localhost:8080.

## Backend Structure
The Backend Structure contains following packages:  
* **Config** - Here all the configuration files are stored like **JWT Filter, Web Security, Swagger Implementation, CORS Filter, JWT Authentication**. This is the     most important package as this will lead to determine which services can be opened by which type of user. All the configuraiton changes can be done in this       package. 
* **Analytics Models** - Here all the codes related to Analytics are present. To view any type of Analytics and if needed to change the structure of the               Analytics, all can be done in this package. This package main objective how ot represent the Analytics in a better and simple way.
* **Controllers** - Controller is responsible for processing incoming requests. It invokes business logic, updates the model and returns the view that should be       rendered. acts as the coordinator between the View and the Model. This package has all the controllers which receives an input from the users via the View,       then processes the user's data with the help of Model and passes the results back to the View.
* **Models** - Model represents an object or JAVA POJO carrying data of the application. It can also have logic to update controller if its data changes.In this       package all the models required for the project are mentioned and all the models are auto generated.
* **Repositories** - This package has the most important usage it provides 2 main benefits: ... The repository improves the reusability of your persistence code,     especially your queries, by encouraging you to implement all persistence operations in one place. That makes them easy to find and to reuse.It is used for         encapsulating storage, retrieval, and search behavior which emulates a collection of objects.
* **Requests** - This package consists of all the requests that can be made by other classes to get the data of the application. This package allows dynamic           creation and invocation of the requests.
* **Responses** - This package consists of all the responses of the requests made by the user of the application.It is an abstract class that contains three           simple methods. The getEntity() method returns the Java object you want converted into an HTTP message body. The getStatus() method returns the HTTP response     code. The getMetadata() method is a MultivaluedMap of response headers.
* **Service**- This package contains all the services which are defined by a set of interfaces and classes. The service contains an interface or an abstract class     that defines the functionality provided by the service.The consumer which is client knows only about the service interface.This package provides a way of a       client to interact with some functionality in the application.

## Features
These are some of the main features in the application/ website of ***REDBANK***

* User should be able to register and login using their email id and password
* Three types of user registrations should be available - blood banks, clinics/hospitals, individuals.
* Blood banks should be able to sell and buy any blood type
* Blood banks and Hospitals should be able to update and track usage and availability of blood
* Any user should be able to buy any blood type from blood banks
* Any user should be able to get list of donors based on search criteria
* Recipient should be able to contact donors
* Blood banks and Hospitals should be able to organise drive for all/selected blood groups by sending notifications to apt donors
* Any user should be able to check availability of any blood type in nearby location
* Indiviidual should be able to register/update himself as a donor or continues as a user
* User should be able to see his donation related activities
* A donor shall be informed of when is he eligible to donate blood next

We have added more additional features in the Application/Website. The link to that site is [Red Bank Feature List](https://docs.google.com/document/d/1qd3vI0HPOJz0xnhEBrer7VZ1xoDE2mdB0rdjQP4Jdcw/edit?usp=sharing)

## Contact
Created By Nineleaps Echo Batch Team 7. The members of the team are :
* Aayush Raj 
* Aditya Tomar 
* Anandhu Prasad 
* Rajarshi Halder
* Vipul Kumar
* Hari Kiran Revanur 
