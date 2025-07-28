# URL Shortener

This is a minimal URL shortener API built with Kotlin and Spring Boot. It uses PostgreSQL to store mappings between long URLs and generated codes.

## Prerequisites

- Java 17 or later
- Docker (for running Postgres)

## Running the app locally

1. **Start Postgres**

   ```bash
   docker run --rm -d --name shortener-db -p 5432:5432 \
   -e POSTGRES_DB=shortener -e POSTGRES_USER=user \
   -e POSTGRES_PASSWORD=password postgres:15-alpine
   ```

2. **Start the application**

Set environment variables (defaults shown):
   ```bash
   export DATABASE_URL="jdbc:postgresql://localhost:5432/shortener"
   export DATABASE_USERNAME="user"
   export DATABASE_PASSWORD="password"
   export SERVICE_PORT=8080
   ```
then run

   ```bash
   ./gradlew bootRun
   ```

The service will listen on `http://localhost:8080` and create the schema automatically.

## API Endpoints

### Create a short URL

`POST /url-mappings`

```json
{ "url": "https://example.com" }
```

Response: **201 Created or 200 OK if the record already exists** and the generated code.

### Resolve a short URL

`GET /url-mappings/{code}`

Response: **200 OK** and the original URL (plain string), or **404 Not Found** if the code does not exist.

## Next steps

- Add unit and integration tests
- Use Flyway for database migrations
- Improve code generation (shorter codes, deduplication)
- Containerise with Docker Compose  
