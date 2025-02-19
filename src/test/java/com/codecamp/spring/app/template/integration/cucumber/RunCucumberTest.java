package com.codecamp.spring.app.template.integration.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.cucumber.junit.platform.engine.Constants.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Suite
@IncludeEngines("cucumber")
@SelectPackages({"com.codecamp.spring.app.template"})
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.codecamp.spring.app.template")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class RunCucumberTest {

}
