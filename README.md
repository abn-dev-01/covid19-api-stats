# covid19-api-stats
It is based on COVID19-API

# What is a feature?
* Develop and implement an API that allows you to find for a specified list of countries: Maximum/minimum number of new cases per day on the selected day/period.
* Receive data via API: https://covid19api.com/
* Restrictions:
** It is necessary to minimize the number of hits from the source site during daily use of the application.
* Technologies: Java 17+, Spring boot/Micronaut

# Technologies
* Spring boot 3.0.1
* Java 17
* Lombok
* H2 + Liquibase
* OpenAPI
* Unit Tests (JUnit, Mockito)

## Principles
* SOLID
* Clean code

### GraphQL 

Run APP with default settings.
Open http://localhost:8989/gql 

Example GraphQL query:

    query {
    statisticsByCountryAndPeriod(
        countries: ["uk","us","ch"],
        dateFrom:"2019-12-01T00:00:00",
        dateTo: "2021-12-01T00:00:00" ) {
            maxCases
            maxCasesDate
            minCases
            minCasesDate
        }
    }




---
#### Telegram: @abn_java
