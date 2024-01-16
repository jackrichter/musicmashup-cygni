# The MusicMashup Application
This is an app that provides the user with music information collected from several API resources,
and present a snippet of a resume of this information.
The information can refer to an artist or a band.

## Usage
The user must provide a valid MBID (MusicBrainz Identifier) to retrieve the information.

## The Application - Backend
This application is developed using Java, by the Spring Boot 3 framework.
The development adopted the Reactive paradigm, as much as it was possible,
intending to communicate with the external APIs in an asynchronously, non-blocking, way
thus seeking to diminish latency wait time.
Further, the following third-party libraries are used:
- Maven 3.9.6
- Jsoup
- Lombok
Developing is done in IntelliJ.

## Running the Application - Backend
By cloning the code from GitHub, one has access to a runnable jar file.
Run the application from the command prompt: java -jar target/musicmashup-0.0.1-SNAPSHOT.jar
After starting the app the user invokes the exposed REST endpoint by using, for example, curl
form the command prompt, using a given MBID:
curl  http://localhost:8181/music/musicbrainz/5b11f4ce-a62d-471e-81fc-a69a8278c7da
or Postman.
