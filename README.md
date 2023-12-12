# Quiz-graphql

This project is a Quiz platform developed with Spring and GraphQL API. Users can interact with the system to create and answer quizzes.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Ensure you have the following installed on your local machine:

* Java JDK
* Maven

### Installation

## Option - 1 : Run Using Docker

With This you don't have to download any jdk, java or any dependency
make sure you have the following installed:
- Docker
- Docker Compose

Now First clone docker-compose.yml file from this project
- In Place of password enter password(any password will work)
and follow this steps


1. Open Terminal and navigate to the Project Directory
   ```bash
   cd your-repo
   ```

2. Run Docker Compose
   ```bash
   docker-compose up
   ```

   Or Run In Background
   ```bash
   docker-compose up -d
   ```

4. You Can stop it with this
   ```bash
   docker-compose down
   ```

## Option - 2 : Run Using This Repository

1. Clone this repo
   ```bash
   git clone https://github.com/KushParsaniya/quiz-graphql.git
   cd REPOSITORY # Replace REPOSITORY with your data
   ```

2. Install dependencies 

   ```bash
   mvn install
   ```

## API Reference

The project uses GraphQL for API service. 

See `schema.graphqls` file with comments for details on the capabilities of the API.

## Running the App

Build and run the app using Maven:
```bash
mvn spring-boot:run
```

By default, the GraphQL API will be available at `http://localhost:8080/graphiql`.

## Examples 

Here are some examples of how to interact with the API:

1. Fetch all questions:

    ```graphql
    query {
       allQuestions {
           id
           que
          //other field you want
       }
    }
    ```

2. Fetch specific question by its unique id:

    ```graphql
    query {
       findOne(id: 1) {
           id
           que
          // other fields that you want
       }
    }
    ```
...
