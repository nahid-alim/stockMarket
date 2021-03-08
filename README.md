# Stock Market Records
This project provides an API which can be used to get a collection of records from the `Dow Jones Index` from 2011.

Prerequisites:
- To run this project you will need `Java`, `Maven` and `PostgreSQL` installed on your system.

Important Notes:
- In order to avoid duplication of records in the database, `id`(also primary key) of each record is the `hash(SHA256)` value of other record's attributes.

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

###Getting Started:
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
- You need to have PostgreSQL installed and running on your machine to run the application.
- You need to add your PostgreSQL username and password in the application.properties file on src/main/resource. 
The lines that must be modified are as follows:
    - `spring.datasource.url=jdbc:postgresql://localhost:5432/postgres`
    - `spring.datasource.username=postgres`
    - `spring.datasource.password=password`
- You can run the application with an IDE:
    - Download the zip or clone the Git repository.
    - Unzip the zip file (if you downloaded one)
    - Open Eclipse IDE
        `File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
        Select the project`
    - Choose the Spring Boot Application file (the one with `@SpringBootApplication`)
    - Right Click on the file and Run as Java Application
    
- Testing API:<br />
You can test the API using `Postman`. The API provides three operations:
    
    - Uploading a file which includes stock records:<br />
    To do so, create a `Post` request with URL `http://localhost:8080/v1/stocks/upload/csv` <br />
    select `Body`, select `form-data`, KEY value is `file` and then choose the file to upload
    
    - Creating a new stock record <br />
    To do so, create a `Post` request with URL `http://localhost:8080/v1/stocks/` and provide the JSON information needed to post the request <br />
    As an example:<br />
    {<br />
        "quarter": 1,<br />
        "stockSymbol": "DD",<br />
        "date": "1/1/2021",<br />
        "openPrice" : "$12.2",<br />
        "highPrice" : "$12.2",<br />
        "lowPrice" : "$12.2",<br />
        "closePrice" : "$12.2",<br />
        "volume" : 12345,<br />
        "percentChangePrice" : 3,<br />
        "percentChangeVolumeOverLastWeek" : 3,<br />
        "previousWeeksVolume" : 123456,<br />
        "nextWeeksOpenPrice" : "$12.2",<br />
        "nextWeeksClosePrice" : "$12.2",<br />
        "percentChangeNextWeeksPrice" : 3,<br />
        "daysToNextDividend" : 22,<br />
        "percentReturnNextDividend" : 20<br />
    }
    
    - Fetching records based on a stock symbol<br />
    To do so, create a `Get` request with URL `http://localhost:8080/v1/stocks/searches/symbol/{stock symbol}`
    
### Documentation:
After running the application, you can access the link below for the documentation and testing:<br />
     `http://localhost:8080/swagger-ui.html`

### Future Improvements:
    - Adding more test cases. Right now there exist only happy path test cases, but more tests on not OK situations should be added.
    - Creating maven profiles (dev, test). In current test cases, the URL is fixed. However, it should be configurable based on the maven profile.
    - Adding Spring HATEOAS to project in order for clients to not hardcode the API URIs.
    - Dockerizing the project, so it includes all the dependencies itself.
    - Adding user authentication using e.g. JWT
    - Improving logging and Swagger documentation configuration
    - Utilizing batch processing for big files.
    - Thinking of a better solution to avoid record duplication on DB, and also informing the client that the record already exists.
    