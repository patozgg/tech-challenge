package io.camunda.example;

import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.error.ConnectorException;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.connector.generator.java.annotation.ElementTemplate;
import io.camunda.example.dto.WeatherConnectorRequest;
import io.camunda.example.dto.WeatherConnectorResult;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OutboundConnector(
    name = "Weather connector", 
    inputVariables = {"city", "location"},
    type = "io.camunda:weather:1")
@ElementTemplate(
    id = "io.camunda.connector.weather.v1",
    name = "Weather connector",
    version = 1,
    description = "Helps to connect to weather API",
    icon = "icon.svg",
    documentationRef = "https://app.tomorrow.io/",
    propertyGroups = {
      @ElementTemplate.PropertyGroup(id = "city", label = "City")
    },
    inputDataClass = WeatherConnectorRequest.class)
public class WeatherConnectorFunction implements OutboundConnectorFunction {

  private static final Logger LOGGER = LoggerFactory.getLogger(WeatherConnectorFunction.class);

  @Override
  public Object execute(OutboundConnectorContext context) {
    final var connectorRequest = context.bindVariables(WeatherConnectorRequest.class);
    return executeConnector(connectorRequest);
  }

  private WeatherConnectorResult executeConnector(final WeatherConnectorRequest connectorRequest) {
    LOGGER.info("Executing weather connector with request {}", connectorRequest);
    String location = connectorRequest.location();
   
  
//https://api.tomorrow.io/v4/weather/realtime?location=Denver&apikey=xNacry2o6Sm1xlbBvi1ZrPNRNM9f87zC
    URI uri = URI.create("https://api.tomorrow.io/v4/weather/realtime?location="+location+"&apikey=xNacry2o6Sm1xlbBvi1ZrPNRNM9f87zC"); 
    HttpRequest httpRequest = HttpRequest.newBuilder() 
        .GET() 
        .uri(uri) 
        .build();
 
    try (HttpClient httpClient = HttpClient.newHttpClient()) { 
     
      HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
      LOGGER.info("Status Code: " + httpResponse.statusCode()); 
      LOGGER.info("Response Body: " + httpResponse.body()); 

      return new WeatherConnectorResult("Response received: " + httpResponse.body());

    } catch (Exception e) { 
      e.printStackTrace();
      throw new ConnectorException("FAIL", e.getMessage());
    } 


   
  }
}
