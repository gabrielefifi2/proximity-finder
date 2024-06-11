# Proximity Finder

Proximity Finder is a Spring Boot application that provides information about airports and weather stations based on their proximity. It leverages external services from the Aviation Weather Center to retrieve the necessary data.

## Features

- Fetches airport information and station data based on proximity.
- Caches responses to improve performance and reduce the number of external API calls.
- Customizable bounding box for proximity queries.
- RESTful API design.

## Prerequisites

- Java 17
- Maven
- An IDE of your choice

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/gabrielefifi2/proximity-finder.git
   cd proximity-finder

2. Build the project with Maven:

    ```bash
    mvn clean install
    ```

3. Run the application:

    ```bash
    mvn spring-boot:run
    ```

## Configuration

Check and edit configurations of the application by the `src/main/resources/application.properties` file.
