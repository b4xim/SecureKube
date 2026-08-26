# SecureKube API

A simple Spring Boot REST API designed as the application component for an end-to-end DevSecOps pipeline on AWS.

## What It Does

SecureKube API is a lightweight REST service that exposes greeting, version, and health endpoints. It serves as a realistic target application for a DevSecOps pipeline featuring Maven, Jenkins, SonarQube, Trivy, Docker, Kubernetes, Prometheus, and Grafana.

## Technologies Used

- **Java 21**
- **Spring Boot 3.3.2**
- **Spring Boot Actuator** — production-ready health and metrics endpoints
- **Maven** — build and dependency management
- **JUnit 5 + Spring MockMvc** — testing

## Project Structure

```
SecureKube/
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java/com/securekube
    │   │   ├── SecureKubeApplication.java        # Application entry point
    │   │   ├── controller
    │   │   │   └── ApiController.java            # REST endpoints
    │   │   ├── dto
    │   │   │   ├── GreetingResponse.java         # /api/hello response
    │   │   │   ├── HealthResponse.java           # /api/health response
    │   │   │   └── VersionResponse.java          # /api/version response
    │   │   └── exception
    │   │       └── GlobalExceptionHandler.java   # Centralized error handling
    │   └── resources
    │       └── application.yml                   # Application configuration
    └── test
        └── java/com/securekube
            ├── SecureKubeApplicationTest.java     # Context load test
            └── controller
                └── ApiControllerTest.java         # Endpoint tests
```

## How to Build

```bash
mvn clean package
```

This compiles the code, runs tests, and produces a JAR in `target/`.

## How to Run Locally

**With Maven:**
```bash
mvn spring-boot:run
```

**With the JAR:**
```bash
java -jar target/securekube-api-1.0.0.jar
```

The application starts on **http://localhost:8080**.

## How to Run Tests

```bash
mvn test
```

## Available API Endpoints

| Method | Endpoint             | Description                          |
|--------|----------------------|--------------------------------------|
| GET    | `/api/hello`         | Returns a greeting message           |
| GET    | `/api/version`       | Returns application name and version |
| GET    | `/api/health`        | Returns application health status    |
| GET    | `/actuator/health`   | Spring Boot Actuator health check    |
| GET    | `/actuator/info`     | Spring Boot Actuator info            |
| GET    | `/actuator/metrics`  | Spring Boot Actuator metrics         |

## Example API Responses

**GET /api/hello**
```json
{
  "message": "Hello from SecureKube!"
}
```

**GET /api/version**
```json
{
  "application": "securekube-api",
  "version": "1.0.0"
}
```

**GET /api/health**
```json
{
  "status": "UP",
  "description": "SecureKube API is running"
}
```

**GET /actuator/health**
```json
{
  "status": "UP",
  "components": {
    "diskSpace": {
      "status": "UP"
    },
    "ping": {
      "status": "UP"
    }
  }
}
```
