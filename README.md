
# Welcome to Temperature Converter!

  

A simple converter which allows us to convert values between C, F, K, R.

  

  

# Running the app

  

Once you have cloned the project, please follow the below steps:

* Run the following command

```bash

$ ./gradlew clean build generateOpenApiDocs && java -jar build/libs/tconverter-0.0.1-SNAPSHOT.jar

```

  

## API Docs

  

You can access the api docs [here](http://localhost:8080/swagger-ui.html)

  

## Actuator

  

You can access the actuator [http://localhost:8080/manage/](http://localhost:8080/manage/health)
| Endpoint | 
|--|
| [Health](http://localhost:8080/manage/health) |
| [Info](http://localhost:8080/manage/info) |
| [Loggers](http://localhost:8080/manage/loggers) |
| [Metrics](http://localhost:8080/manage/metrics) |
