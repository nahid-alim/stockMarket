# Stock Market Records
This project provides an API which can be used to get a collection of records from the Dow Jones Index from 2011.

Prerequisites:
- To run this project you will need Java, Maven and PostgreSQL installed on your system.

Important Notes:
- In order to avoid duplication of records in database, id(also primary key) of each record is hash(SHA256) value of other record's attributes.

Used Technologies and Open-source libraries:
 - Spring Boot
 - Spring DATA JPA
 - Spring WEB
 - PostgreSQL
 - OpenCSV
 - Swagger
 - Log4j2
 - JUnit
 - Lombok

External Tools:
- Postman

Getting Started:
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
- You need to have PostgreSQL installed and running on your machine to run the application.
- You need to add your PostgreSQL username and password in the application.properties file on src/main/resource. 
The lines that must be modified are as follows:
    - spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
    - spring.datasource.username=postgres
    - spring.datasource.password=password
- Now you can run the application with an IDE:
    - Download the zip or clone the Git repository.
    - Unzip the zip file (if you downloaded one)
    - Open Eclipse IDE
        File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
        Select the project
    - Choose the Spring Boot Application file (the one with @SpringBootApplication)
    - Right Click on the file and Run as Java Application
    
- Testing API:
    You can test the API using Postman.
    The API provides three operations:
    
    - Uploading a file which includes stock records:
    To do so, create a Post request with URL http://localhost:8080/v1/stocks/upload/csv
    select 'Body' -> select 'form-data' -> KEY value is 'file' and then choose the file to upload
    
    - Creating a new stock record
    To do so, create a Post request with URL http://localhost:8080/v1/stocks/ and provide the JSON information needed to post the request
    As an example:
    {
        "quarter": 1,
        "stockSymbol": "DD",
        "date": "1/1/2021",
        "openPrice" : "$12.2",
        "highPrice" : "$12.2",
        "lowPrice" : "$12.2",
        "closePrice" : "$12.2",
        "volume" : 12345,
        "percentChangePrice" : 3,
        "percentChangeVolumeOverLastWeek" : 3,
        "previousWeeksVolume" : 123456,
        "nextWeeksOpenPrice" : "$12.2",
        "nextWeeksClosePrice" : "$12.2",
        "percentChangeNextWeeksPrice" : 3,
        "daysToNextDividend" : 22,
        "percentReturnNextDividend" : 20
    }
    
    - Fetching records based on a stock symbol
    To do so, create a Get request with URL http://localhost:8080/v1/stocks/searches/symbol/{stock symbol}
    
- Documentation:
    After running the application, you can access the link below for the documentation and testing:
    - http://localhost:8080/swagger-ui.html

- Future Improvements:
    - Adding more test cases. Right now there exist only happy path test cases, but more tests on not OK situations should be added.
    - Creating maven profiles (dev, test). In current test cases, the URL is fixed. However, it should be configurable based on the maven profile.
    - Adding Spring HATEOAS to project in order for clients to not hardcode the API URIs.
    - Dockerizing the project, so it includes all the dependencies itself.
    - Adding user authentication using e.g. JWT
    - Improving logging and Swagger documentation configuration
    - Utilizing batch processing for big files.
    