package com.example.app.cucumber;

import com.example.app.Application;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@CucumberContextConfiguration
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class HealthStepDefinitions {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<Map> response;

    @Given("the application is running")
    public void theApplicationIsRunning() {
        // Application is already running due to @SpringBootTest configuration
        assertTrue(port > 0, "Application should be running on a port");
    }

    @When("I call the health check endpoint")
    public void iCallTheHealthCheckEndpoint() {
        String url = "http://localhost:" + port + "/health";
        response = restTemplate.getForEntity(url, Map.class);
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int expectedStatus) {
        assertEquals(HttpStatus.valueOf(expectedStatus), response.getStatusCode());
    }

    @Then("the response should contain status {string}")
    public void theResponseShouldContainStatus(String expectedStatus) {
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertEquals(expectedStatus, body.get("status"));
    }

    @Then("the response should contain service name {string}")
    public void theResponseShouldContainServiceName(String expectedServiceName) {
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertEquals(expectedServiceName, body.get("service"));
    }

    @Then("the response should contain a timestamp")
    public void theResponseShouldContainATimestamp() {
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertTrue(body.containsKey("timestamp"));
        assertNotNull(body.get("timestamp"));
    }

    @Then("the response should be valid JSON")
    public void theResponseShouldBeValidJson() {
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof Map);
    }

    @Then("the response should have the required fields")
    public void theResponseShouldHaveTheRequiredFields() {
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertTrue(body.containsKey("status"));
        assertTrue(body.containsKey("timestamp"));
        assertTrue(body.containsKey("service"));
    }
}