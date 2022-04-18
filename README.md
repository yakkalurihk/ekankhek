# ekankhek
User file upload and sharing app

Spring boot application with MySql DB

1. Clone the repo from development branch
2. The MySql file should be imported to local MySql 
3. This has Three Tables:
    3.1 User - Main Table to provide site access
    3.2 User_role - Table to Maintain User Roles.
    3.3 Datauploads - Table to Store the uploaded filename with title and description
4. Import the Project into Eclipse - Change the Runtime JRE to SDK to latest
5. build using mvn clean install -Dmaven.test.skip=true
6. Run using java -jar <filename.jar>


Available Users with Passwords:
yakkalurihk@gmail.com	test1234
admin@admin.com	admin
testuser@gmail.com	test1234
