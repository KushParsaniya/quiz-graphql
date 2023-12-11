# Quiz-graphql

This project is a Quiz platform developed with Spring and GraphQL API. Users can interact with the system to create and answer quizzes.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Ensure you have the following installed on your local machine:

* Java JDK
* Maven

### Installation

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
mvn spring-boot:ru
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
       }
    }
    ```
...
