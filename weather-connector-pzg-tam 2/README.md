# Weather Connector Example

A Camunda 8 example connector working with https://app.tomorrow.io/



## How to Run

1. Download Github project and import into your IDE
2. Go into the application.properties file under src/test/resources and update with your own Camunda 8 SaaS credentials. 
3. Go to src/main/resources folder and drag and drop the bpmn, json and form file into your Camunda Web Modeler folder. Make sure to create a new folder beforehand. 
4. Go into the process model via the Camunda Web Modeler and click on the run button found in the upper right hand corner. Type in the city for which you need the weather information. This will start a process instance. 
5. Go into the LocalConnectorRuntime file located in src/test/java and click run via your IDE. This will start the connector. You should see weather information in your IDE console indicating that a connector instance was completed.  
6. Go into Camunda Operate and inspect the process. Verify that the process instance is now at the User Task and check the result.  

Email patricio.zambrano@camunda.com if you run into any issues. 



### Output

"Response received: " + http response body

The response should look something like this:

######{"response":"Response received: {\"data\":{\"time\":\"2024-07-02T22:22:00Z\",\"values\":{\"cloudBase\":3.9,\"cloudCeiling\":null,\"cloudCover\":13,\"dewPoint\":-0.69,\"freezingRainIntensity\":0,\"humidity\":13,\"precipitationProbability\":0,\"pressureSurfaceLevel\":835,\"rainIntensity\":0,\"sleetIntensity\":0,\"snowIntensity\":0,\"temperature\":30.88,\"temperatureApparent\":28.83,\"uvHealthConcern\":1,\"uvIndex\":4,\"visibility\":16,\"weatherCode\":1100,\"windDirection\":290.81,\"windGust\":7.38,\"windSpeed\":3.81}},\"location\":{\"lat\":39.739234924316406,\"lon\":-104.98486328125,\"name\":\"Denver, Colorado, United States\",\"type\":\"administrative\"}}"}


