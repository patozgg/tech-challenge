package io.camunda.example.dto;

import io.camunda.connector.generator.java.annotation.TemplateProperty;
import io.camunda.connector.generator.java.annotation.TemplateProperty.PropertyType;
import jakarta.validation.constraints.Positive;

public record WeatherConnectorRequest(
     @TemplateProperty(group = "city", type = PropertyType.String) String location)
    {}
