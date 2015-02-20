# Birt2AirVantageConnector
A connector example to allow BIRT Designer to get data from AirVantage.

# Getting started

1. Add this project in the Birt Designer
2. Export the jar (using the contextual menu from the Package Explorer in the Java perspective) into your Birt Project
3. Create a Data sources. In Runtime Properties, and add all the jars from the lib directory (included one exported in step 2). 
4. Create a new DataSet
 - Select Pojo and supply a name
 - Fill the POJO Data Set Class Name with the connector class name (eg. com.birt.airvantage.SystemConnector)
 - Fill the POJO Class Name with the matching object (eg. AVSystem)
 - Add your supported field in your BIRT object
 - Click on Finish

 Use the object in your BIRT report!