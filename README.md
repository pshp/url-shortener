# URL Shortener

A minimal URL-shortening API built with Kotlin and Spring Boot.  
It stores mappings between long URLs and generated codes in PostgreSQL.

---

## Prerequisites

- Java 17+
- Docker (for Postgres)

---

## Running locally

1. **Start Postgres**

   ```bash
   docker run --rm -d --name shortener-db \
     -p 5432:5432 \
     -e POSTGRES_DB=shortener \
     -e POSTGRES_USER=user \
     -e POSTGRES_PASSWORD=password \
     postgres:15-alpine
   ```

2. **Start the application**

   ```bash
   ./gradlew bootRun
   ```

   The service listens on **`http://localhost:8080`** and creates the schema automatically.

---

## API

### Create a short URL
`POST /url-mappings`

```json
{ "url": "https://example.com/very/long/path" }
```

- Returns **201 Created**
- Body → generated code (plain string)

### Resolve a short URL
`GET /url-mappings/{code}`

- Returns **200 OK** and the original URL (plain string)
- Returns **404 Not Found** if the code doesn’t exist

Example:

```bash
# shorten
curl -X POST -H "Content-Type: application/json" \
  -d '{"url":"https://example.com"}' \
  http://localhost:8080/url-mappings

# resolve
curl http://localhost:8080/url-mappings/<code>
```

---

## Next steps

- Add unit / integration tests
- Use Flyway for schema migrations
- Improve code generation (shorter codes, deduplication)
- Provide Docker Compose for app + Postgres
