# Enterprise Form Builder

## Prerequisites

- Java 11
- PostgreSQL
- Gradle

## Setup

1. Clone the repository:

    ```bash
    git clone <repository-url>
    cd enterprise-form-builder
    ```

2. Update the PostgreSQL credentials in `src/main/resources/application.properties`.

3. Create the PostgreSQL database and run the schema script:

    ```bash
    psql -U your_db_username -d your_db_name -a -f src/main/resources/schema.sql
    ```

4. Build and run the application:

    ```bash
    ./gradlew bootRun
    ```

5. Run tests:

    ```bash
    ./gradlew test
    ```

## Endpoints

- `POST /api/forms` - Create a new form
- `GET /api/forms/{id}` - Get a form by ID
- `GET /api/forms` - Get all forms

## Example Request

### Create Form

```bash
curl -X POST http://localhost:8080/api/forms -H 'Content-Type: application/json' -d '{
  "title": "Leave Application Form",
  "fields": [
    {
      "label": "Reason for Leave",
      "isRequired": true,
      "type": "TEXT"
    },
    {
      "label": "Number of Days",
      "isRequired": true,
      "type": "NUMBER",
      "configuration": "{\"min\": 1, \"max\": 30, \"decimalPlaces\": 0}"
    }
  ]
}'
