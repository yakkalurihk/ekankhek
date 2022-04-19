# ekankhek
User file upload and sharing app

## Spring boot application with MySql DB

1. Clone the repo from **development branch**
2. The MySql file should be imported to local MySql
    - **assignment.sql** file has the Quesries to the Table
    - This has Three Tables:
        - User - Main Table to provide site access
        - User_role - Table to Maintain User Roles
        - Datauploads - Table to Store the uploaded filename with title and description
3. Change the **application.properties** file to add database details
    - ```
      spring.datasource.url=jdbc:mysql://<HOST>:<POST>/< ADD DATABASE NAME HERE >
      spring.datasource.username=< DATABASE USERNAME>
      spring.datasource.password=<DATABASE PASSWORD> 
      ``` 
5. Import the Project into Eclipse - Change the Runtime JRE to SDK to latest
6. build using ``` mvn clean install -Dmaven.test.skip=true ```
7. Run using ``` java -jar <filename.jar> ```


Available Users with Passwords: # This will be available after the MySQl file Import
- Test User 1
    - Email: yakkalurihk@gmail.com	 
    - password: test1234
- Test User 2 
    - Email: admin@admin.com	
    - Password: admin
- Test User 3
    - Email: testuser@gmail.com	
    - Password: test1234

## Operations Supported
- Login & Logout
- File Upload with Title and Description
    - Shows progress of upload
- List of file uploaded , specific to the individual users
- Delete the file uploaded
- Share the File with link
- Download the File

## UI Information

- Bootstrap 5 
- Jquery 3.6
- Used Sample Login Template and Admin Template
- Thymeleaf template 

## Queries

**User Table**
```
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(145) DEFAULT NULL,
  `last_name` varchar(145) DEFAULT NULL,
  `email` varchar(245) DEFAULT NULL,
  `password` varchar(145) DEFAULT NULL,
  `active` tinyint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `user` VALUES (1,'Hari','Yakkaluri','yakkalurihk@gmail.com','test1234',1,'2022-04-18 05:39:00','2022-04-18 05:39:00'),(2,'admin','admin','admin@admin.com','admin',1,'2022-04-18 05:39:00','2022-04-18 05:39:00'),(3,'test','test','testuser@gmail.com','test1234',1,'2022-04-18 05:39:00','2022-04-18 05:39:00');

```

**User Role Table**
```
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user` int DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userrole_idx` (`user`),
  CONSTRAINT `userrole` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
);

INSERT INTO `user_role` VALUES (1,1,'ROLE_USER'),(2,2,'ROLE_ADMIN'),(3,3,'ROLE_USER');
```

**Datauploads Table**
```
CREATE TABLE `datauploads` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `filename` text,
  `user` int DEFAULT NULL,
  `title` varchar(245) DEFAULT NULL,
  `description` text,
  `sharecode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userfile_idx` (`user`),
  CONSTRAINT `userfile` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
);
```
