package com.niit.demo.stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.demo.models.ErrorResult;
import com.niit.demo.models.RatesApiResult;
import com.niit.demo.models.RatesResult;
import io.cucumber.core.options.CucumberProperties;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

import static com.niit.demo.utils.CommonUtils.dateIsFutureDate;
import static com.niit.demo.utils.CommonUtils.getDate;
import static io.restassured.RestAssured.given;

/**
 * This is the step definition of all the feature file steps
 */
public class StepDef {

    private Scenario scenario = null;
    private final Map<String, String> cucumberProps = CucumberProperties.fromPropertiesFile();
    private String basePath = cucumberProps.get("rates_api_foreign_exchange_rates_base_path");
    private Response response = null;
    private String url = null;
    private String path = null;

    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("^Valid endpoint for latest foreign exchange rates api$")
    public void valid_endpoint_for_latest_foreign_exchange_rates_api() {
        this.path = "latest";
        this.url = basePath + "/" + path;
    }

    @Given("^Invalid endpoint with path (.+) for foreign exchange rates api$")
    public void invalid_endpoint_foreign_exchange_rates_api(String path) {
        this.path = path;
        this.url = basePath + "/" + path;
        scenario.log("urls is " + url);
    }

    @Given("^Valid endpoint for specific date (.+) foreign exchange rates api$")
    public void valid_endpoint_for_specific_date_foreign_exchange_rates_api(String date) {
        this.path = date;
        this.url = basePath + "/" + date;
        scenario.log("url is " + url);
    }

    @When("^Endpoint is accessed to (.+) the results$")
    public void endpoint_is_accessed_to_get_the_results(String method) {
        switch (method) {
            case "get":
                response = given().when().relaxedHTTPSValidation().get(url);
                break;
            case "post":
                //to be implemented if required
                break;
        }
    }

    @Then("^The response status code should be (.+)$")
    public void the_response_status_code_should_be(int code) {
        Assert.assertEquals(code, response.getStatusCode());
        scenario.log("status code is " + response.getStatusCode());
    }

    @Then("^The response should contain all the expected details$")
    public void the_response_should_contain_latest_dates() {
        scenario.log(response.getBody().asString());
        ObjectMapper objectMapper = new ObjectMapper();
        RatesApiResult ratesApiResult;
        try {
            ratesApiResult = objectMapper.readValue(response.getBody().asString(), RatesApiResult.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("unexpected response coming " + response.getBody().asString());
        }


        Assert.assertNotNull(ratesApiResult.getBase());
        Assert.assertNotNull(ratesApiResult.getRatesResult());
        String date = ratesApiResult.getDate();
        Assert.assertNotNull(date);
        RatesResult ratesResult = ratesApiResult.getRatesResult();

        Assert.assertNotNull(ratesResult.getGbp());
        Assert.assertNotNull(ratesResult.getHkd());
        Assert.assertNotNull(ratesResult.getIdr());
        Assert.assertNotNull(ratesResult.getIls());
        Assert.assertNotNull(ratesResult.getDkk());
        Assert.assertNotNull(ratesResult.getInr());
        Assert.assertNotNull(ratesResult.getChf());
        Assert.assertNotNull(ratesResult.getMxn());
        Assert.assertNotNull(ratesResult.getCzk());
        Assert.assertNotNull(ratesResult.getSgd());
        Assert.assertNotNull(ratesResult.getThb());
        Assert.assertNotNull(ratesResult.getHrk());
        Assert.assertNotNull(ratesResult.getMyr());
        Assert.assertNotNull(ratesResult.getCny());
        Assert.assertNotNull(ratesResult.getBgn());
        Assert.assertNotNull(ratesResult.getPhp());
        Assert.assertNotNull(ratesResult.getSek());
        Assert.assertNotNull(ratesResult.getPln());
        Assert.assertNotNull(ratesResult.getZar());
        Assert.assertNotNull(ratesResult.getCad());
        Assert.assertNotNull(ratesResult.getIsk());
        Assert.assertNotNull(ratesResult.getBrl());
        Assert.assertNotNull(ratesResult.getRon());
        Assert.assertNotNull(ratesResult.getTRY());
        Assert.assertNotNull(ratesResult.getJpy());
        Assert.assertNotNull(ratesResult.getRub());
        Assert.assertNotNull(ratesResult.getKrw());
        Assert.assertNotNull(ratesResult.getUsd());
        Assert.assertNotNull(ratesResult.getHuf());
        Assert.assertNotNull(ratesResult.getAud());

        if (path.equals("latest")) {
            Assert.assertEquals(getDate(0, "yyyy-MM-dd"), date);
        } else {
            boolean checkDateIsFuture = dateIsFutureDate(path, "yyyy-MM-dd");
            if (checkDateIsFuture) {
                Assert.assertEquals(getDate(0, "yyyy-MM-dd"), date);
            } else {
                Assert.assertEquals(path, ratesApiResult.getDate());
            }

        }
    }

    @Then("^Error response should appear with code (.+)$")
    public void error_response_should_appear_with_code(int code) {
        scenario.log("response code is " + code);
        scenario.log(response.getBody().asString());
        Assert.assertEquals(code, response.getStatusCode());
        ObjectMapper objectMapper = new ObjectMapper();
        ErrorResult errorResult = null;
        try {
            errorResult = objectMapper.readValue(response.getBody().asString(), ErrorResult.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("error response not in proper format" + e.getMessage());
        }
        Assert.assertEquals("time data '" + path + "' does not match format '%Y-%m-%d'", errorResult.getError());
    }

}


