# Kodo Form Builder Microservice

The Kodo Form Builder is a microservice designed to manage dynamic forms. This microservice is built using Spring Boot and connects to a PostgreSQL database. It provides RESTful endpoints to create, retrieve, and manage forms and their fields.

## Prerequisites

- Docker
- Docker Compose

## Project Structure

```plaintext
formbuilder
├── docker-compose.yml
├── Dockerfile
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── kodo
│   │   │           └── formbuilder
│   │   │               ├── KodoFormBuilderApplication.java
│   │   │               ├── config
│   │   │               │   └── ValidationConfig.java
│   │   │               ├── controller
│   │   │               │   └── FormController.java
│   │   │               ├── model
│   │   │               │   ├── Field.java
│   │   │               │   ├── Form.java
│   │   │               │   └── enums
│   │   │               │       └── FieldType.java
│   │   │               ├── repository
│   │   │               │   └── FormRepository.java
│   │   │               ├── service
│   │   │               │   ├── FormService.java
│   │   │               │   ├── ValidationService.java
│   │   │               │   └── validation
│   │   │               │       ├── FieldValidationStrategy.java
│   │   │               │       ├── NumberFieldValidationStrategy.java
│   │   │               │       └── TextFieldValidationStrategy.java
│   │   │               ├── util
│   │   │                   ├── Constants.java
│   │   │                   └── ErrorMessages.java
│   │   ├── resources
│   │       ├── application.properties
│   │       ├── application_prod.properties
│   │       ├── logback.xml
│   │       └── schema.sql
│   └── test
│       └── java
│           └── com
│               └── kodo
│                   └── formbuilder
│                       └── KodoFormBuilderApplicationTests.java
│                       └── controller
│                          └── FormControllerTest.java
│                       └── service
│                          └── FormServiceTest.java
│                          └── ValidationServiceTest.java


```
## Building project

1. **Build the project using mvn** 
   ```bash
   mvn clean install
   ```

## Running the Microservice

1. **Build the Docker image:**

   ```bash
   mvn compile jib:dockerBuild
   ```

2. **Run the Docker Compose:**

   ```bash
   docker-compose up -d
   ```

3. **Access the API documentation:**

   Open your browser and go to [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to view the API documentation.

## Configuration

The `application.properties` file contains configurations for the PostgreSQL database:

```properties
spring.application.name=Kodo Form Builder

spring.datasource.url=jdbc:postgresql://localhost:5432/formbuilder
spring.datasource.username=postgres
spring.datasource.password=admin

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Text field configurations
field.text.max-length=255
field.text.min-length=1
field.text.pattern=^[a-zA-Z0-9 ]*$

# Number field configurations
field.number.max-value=1000000
field.number.min-value=0
field.number.integer-only=true
field.number.decimal-places=2
```

## Health Check

The Docker Compose file includes a health check for the PostgreSQL container to ensure that the service only starts once the database is ready.

## Running Tests

To run the tests, use the following command:

```bash
./mvn test
```

## Endpoints

### Create Form

```http
POST /api/forms
```

Request Body:
```json
{
  "title": "Sample Form",
  "fields": [
    {
      "label": "Name",
      "isRequired": true,
      "type": "TEXT",
      "value": "Harshad"
    },
    {
      "label": "Age",
      "isRequired": false,
      "type": "NUMBER",
      "value": "29"
    }
  ]
}
```

### Get Form by ID

```http
GET /api/forms/{id}
```

### Get All Forms

```http
GET /api/forms
```

## License

This project is licensed under the MIT License.
