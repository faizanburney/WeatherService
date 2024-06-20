## Features assumed to be out of scope to save time:
```
security
data validations
extensive testing (intergration and unit tests of classes).
Api documention
```

## Project setup

What You Need
```
Java 17 or later
Gradle 7.5+ 
Docker
```
You can also import the code straight into your IDE:
```
IntelliJ IDEA
VSCode
```

To run kafka and zookeeper just execute the following command in project base. Make sure that Docker is installed and running.
```
docker-compose up
```

To run the application, run the following command in a terminal window (in the complete) directory:
```
./gradlew bootRun
```

To send a user event on Kafka, you can directly send an event to kafka topic "user-topic" or use the rest api endpoint with post:
```
http://localhost:8080/v1/locations
with json body structure like :
{
	"username":"john",
	"longitude":55,
	"latitude":66
}
```

Command to view events of kafka in console :
```
kafka-console-consumer --bootstrap-server localhost:29092 --topic user-topic --from-beginning
```

The application will read the event from kafka stream and fetch user's weather info using geo coordinates.
We can view all of user's data using the following get endpoint.
```
http://localhost:8080/v1/weather
```

To execute usit tests run:
```
./gradlew bootTestRun
```
