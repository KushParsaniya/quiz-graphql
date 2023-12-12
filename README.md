# Quiz-graphql

* This project is a Quiz platform developed with Spring and GraphQL API. Users can interact with the system to create and answer quizzes.
* User Can Generate Question Automatically with language(like java,python,...) name and specific topic(oop,function,array,collection...) It Uses Google's Bard Api key to 
 generate Questions.

## Getting Started

* These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Ensure you have the following installed on your local machine
(If You Use Docker(option 1) then don't need this):

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

3. You Can stop it with this
   ```bash
   docker-compose down
   ```
By default, the GraphQL API will be available at `http://localhost:8080/graphiql`.

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

3. Running the App
   Build and run the app using Maven:
   ```bash
   mvn spring-boot:run
   ```

By default, the GraphQL API will be available at `http://localhost:8080/graphiql`.

## API Reference

The project uses GraphQL for API service. 

See `schema.graphqls` file with comments for details on the capabilities of the API.


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
3. Generate Question Automatically Uses Google Bard Api key
   ```graphql
   mutation {
    generateOneAutomatically(
          language: "java",
          type: "Java Stream"
     ){
          id
          que
          option1
          option2
          option3
          option4
          ans
          type
          difficulty
        }   
      }
   ```
   
