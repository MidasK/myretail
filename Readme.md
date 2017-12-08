## MyRetail Products App
myretail is an API for myRetail company's Product line.
Get API aggregates product data from multiple sources and returns it as JSON to the caller.
Put API takes a JSON request body as input, and updates the product’s price in the data store.

## Dependencies
All dependencies are mentioned in build.gradle file. This can be run to download all dependencies required by the app. 

## For Unit testing
Run MockMVC tests, Controller tests, and other Unit tests. 

## For App testing
All endpoints can be tested via a RestClient like PostMan. 

## For running
- MongoDB : MongoDB should be running on either local machine or another server. Following parameters need to be provided in application.properties (sample from local implementation) :
 
     spring.data.mongodb.host=localhost
     spring.data.mongodb.port=27017
     spring.data.mongodb.database=myRetailProducts

Please execute the script 'MongoDBSetup' provided in dbscripts folder as a prerequisite for setting up the database.       

- Server properties : details like server port and active profiles can be mentioned. For this app, only provided server port : 
      
     server.port=8089

## For use in Production Environment
- Swagger documentation
- Load Balancing for scaling
- Keep properties and configuration in a separate git repo and separate config server
- User Authentication & Authorization
- Endpoints for inserting data 
- Release cycle and notes