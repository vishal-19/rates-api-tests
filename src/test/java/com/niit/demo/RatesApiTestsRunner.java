package com.niit.demo;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Main runner class to execute features
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:features/rates_api_tests.feature"}, tags = "@rates", plugin = "html:target/rates_api.html")
public class RatesApiTestsRunner {
}
