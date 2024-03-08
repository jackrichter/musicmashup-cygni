# The MusicMashup Application
This is an app that provides the user with music information collected from several API resources,
and present a snippet of a resume of this information.
The information can refer to an artist or a band.

## Usage
The user must provide a valid MBID (MusicBrainz Identifier) to retrieve the information.

## The Application - Backend
This application is developed using Java, with the Spring 6 framework and the Spring Boot 3 tool.
The development adopted the Reactive paradigm, as much as it was possible,
intending to communicate with the external APIs in an asynchronous, non-blocking, way
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

## Running the Application from the browser:
A front end was added to this application.
See instructions on running the app in the README.md file
in the "musicmashup-react" repo at:
https://github.com/jackrichter/musicmashup-react/tree/main

