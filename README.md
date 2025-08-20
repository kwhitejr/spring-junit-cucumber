# Spring Boot + JUnit 5 + Cucumber Demo

A sample implementation demonstrating integration of Java 17, Spring Boot 3.2.0, JUnit 5, and Cucumber for BDD testing.

## Prerequisites

- **Java 17** or higher
- **Maven 3.8+**
- **Git**

## Project Structure

```
src/
├── main/java/com/example/app/
│   ├── Application.java                    # Spring Boot main class
│   └── controller/
│       └── HealthController.java           # Health check REST endpoint
├── main/resources/
│   └── application.properties              # Application configuration
└── test/
    ├── java/com/example/app/
    │   ├── controller/
    │   │   └── HealthControllerTest.java   # JUnit 5 unit tests
    │   └── cucumber/
    │       ├── CucumberTestRunner.java     # Cucumber test runner
    │       └── HealthStepDefinitions.java  # BDD step definitions
    └── resources/
        ├── application-test.properties     # Test configuration
        └── features/
            └── health.feature              # Cucumber feature files
```

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/kwhitejr/spring-junit-cucumber.git
cd spring-junit-cucumber
```

### 2. Build the Project
```bash
mvn clean compile
```

### 3. Run Tests
```bash
# Run all tests (JUnit + Cucumber)
mvn test

# Run only JUnit tests
mvn test -Dtest=HealthControllerTest

# Run only Cucumber BDD tests
mvn test -Dtest=CucumberTestRunner
```

### 4. Start the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 5. Test the Health Endpoint
```bash
curl http://localhost:8080/health
```

Expected response:
```json
{
  "service": "spring-junit-cucumber-demo",
  "status": "UP",
  "timestamp": "2025-08-19T22:47:44.040480270"
}
```

## Testing

### JUnit 5 Tests
- **Location**: `src/test/java/com/example/app/controller/`
- **Framework**: JUnit 5 with Spring Boot Test
- **Purpose**: Unit testing of REST controllers

### Cucumber BDD Tests
- **Features**: `src/test/resources/features/`
- **Step Definitions**: `src/test/java/com/example/app/cucumber/`
- **Purpose**: Behavior-driven testing with Gherkin scenarios

#### Sample BDD Scenario
```gherkin
Scenario: Health endpoint returns successful response
  Given the application is running
  When I call the health check endpoint
  Then the response status should be 200
  And the response should contain status "UP"
  And the response should contain service name "spring-junit-cucumber-demo"
  And the response should contain a timestamp
```

## Test Reports

### HTML Reports
Cucumber generates comprehensive HTML reports after test execution:

```bash
mvn test -Dtest=CucumberTestRunner
```

**Report Location**: `target/cucumber-reports/report.html`

Open the report in your browser to view:
- ✅ Test execution summary
- ✅ Scenario pass/fail status
- ✅ Step-by-step execution details
- ✅ Execution timings
- ✅ Interactive test results

### Surefire Reports
Maven Surefire generates standard test reports:

**Report Location**: `target/surefire-reports/`

## Configuration

### Application Properties
- **Main**: `src/main/resources/application.properties`
- **Test**: `src/test/resources/application-test.properties`

### Maven Dependencies
Key dependencies configured in `pom.xml`:
- Spring Boot 3.2.0
- JUnit 5 (via spring-boot-starter-test)
- Cucumber 7.15.0
- JUnit Platform Suite API

## Development

### Adding New Tests

#### JUnit Test
1. Create test class in `src/test/java/com/example/app/controller/`
2. Use `@SpringBootTest` and `@AutoConfigureMockMvc`
3. Inject `MockMvc` for endpoint testing

#### Cucumber Scenario
1. Add feature file in `src/test/resources/features/`
2. Write Gherkin scenarios
3. Implement step definitions in `src/test/java/com/example/app/cucumber/`

### Running Specific Tests
```bash
# Run tests by class name
mvn test -Dtest=HealthControllerTest

# Run tests by method pattern
mvn test -Dtest=HealthControllerTest#testHealthEndpoint

# Run Cucumber with specific tags
mvn test -Dtest=CucumberTestRunner -Dcucumber.filter.tags="@health"
```

## Technology Stack

- **Java**: 17
- **Spring Boot**: 3.2.0
- **Testing**: JUnit 5, Cucumber 7.15.0
- **Build**: Maven 3.8+
- **Packaging**: JAR

## License

This project is a demonstration/sample implementation for educational purposes.

## Contributing

This is a sample project. Feel free to fork and modify for your own learning and development needs.