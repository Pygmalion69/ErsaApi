# ErsaApi
REST API (using Spring Boot) for storage and retrieval of environmental variables

#### Add application.properties

src/main/resources/application.properties
```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://services.yourdomain.tld:3306/ersa
spring.datasource.username=...
spring.datasource.password=...
```

## API

Note: timestamps are UNIX epochs in seconds.

#### Base URL
----
   http://services.yourdomain.tld:8080/ersa/

### Reading
----
  Accepts a reading JSON object to store

#### URL

    /reading/
    
#### Method

    `POST`
    
#### Data Params

    `{"origin":<string>,temperature:<double>,humidity:<double>}`
    
### Latest
----
  Returns a list of the latest readings (for all origins)
  
#### URL

    /latest/
    
#### Method

    `GET`
    
### Range
----
  Returns a list of readings by origin within a time range
  
#### URL

    /range/
    
#### Method

    `GET`
    
#### URL Params

    String `origin`
    Long `minTime`
    Long `maxTime`
