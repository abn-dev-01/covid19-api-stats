# covid19-api-stats

Minimum and Maximum statistics on a period.
(Data source is COVID19-API)

# What is a feature?

* Develop and implement an API that allows you to find for a specified list of countries: Maximum/minimum number of new
  cases per day on the selected day/period.
* Receive data via API: https://covid19api.com/
* Restrictions:
  ** It is necessary to minimize the number of hits from the source site during daily use of the application.
* Technologies: Java 17+, Spring boot/Micronaut

# Technologies

* Spring boot 3.0.1
* Java 17
* Lombok
* GraphQL
* Jooq
* FlywayDB
* Docker-Compose
* PostgreSQL database
* Unit Tests (JUnit, Mockito, Spring-tests, Jacoco)

---

# Technical details

## Quick start

1) Prepare Docker and Docker compose for your environment.
    1) Docker installation process: https://docs.docker.com/engine/install/
    2) Docker Compose installation process: https://docs.docker.com/compose/install/
2) Open terminal (cmd, etc) and CD to the folder {project}/docker/pgsql/. Configure the docker-compose.yaml according
   your folder paths. Option `volumes` change with your folder `"{replace-this}:/var/lib/postgresql/data"`
3) Run a command  `docker-compose up`. Must work and will be there a new listener on port 55433.
4) Download Java17+ and prepare it for running apps (add executable files folder into PATH env variable)
   i.e. https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html
5) Check you have JDK 17+ running command in terminal
   `java --version`
6) Build the project: `.\gradlew clean buildJar`.
7) If `BUILD SUCCESSFUL` on last step, run the Application one of these ways:
    * `.\gradlew bootRun` or
    *     In root of the project run:
          #> java -jar build\libs\covid19-api-stats-0.0.1-SNAPSHOT.jar
    *     If you`d like to run the App in background use this way: 
          #> javaw -jar build\libs\covid19-api-stats-0.0.1-SNAPSHOT.jar

8) Open in your web browser (FF, IE, Opera, Safari, etc) `http://localhost:8989/gql`. Example of GraphQL query is below.

### GraphQL

Run APP with default settings.
Open http://localhost:8989/gql

Example GraphQL query: Getting MinMax statistics for Poland and China in period `2022-03-18` ~ `2022-03-19`.

    query {
        statisticsByCountryAndPeriod(
        countries: ["china","poland"],
        dateFrom:"2022-03-18T00:00:00",
        dateTo: "2022-03-19T00:00:00"
    ) {
        key
            value{
                maximumCases
                maximumCasesDates
                minimumCases
                minimumCasesDates
            }
        }
    }

---

## Database

* PostgreSQL
* JDBC url and details in a properties file. fo

---

#### Telegram: @abn_java
