# SalesLoft Exercise Project

This project uses AngularJS for the frontend and Java, Spring Framework, and Gradle for the backend.

## Getting Started
### Prerequisites
This project has only been tested on a MacOS. Might not work on Windows OS. 
Must have the port 8080 and 8081 open.
Must have open internet access
Must have NPM installed
Must have NPM package concurrently installed, if not, use `npm i concurrently --save-dev` to install

### Running Locally
* Install website dependencies with the command `npm install`
* Define API Key by setting it in the environment variables. Use the command `export API_KEY={API_KEY}`
* To run the webserver and backend server use the command `npm start`. This will build the backend server and the start the frontend and backend.
* Go to http://localhost:8000/ to access the website

#### Running Web Server
To run the webserver by itself, use the command 'npm run start-server'

#### Running Backend Server
To build the backend server us the command `./gradlew clean build`
Define API Key by setting it in the environment variables. Use the command `export API_KEY={API_KEY}` The backend server won't start unless this is set'
To run the backend server use the command `./gradlew clean bootrun`

##TODO
Here is a list of changes that would be made to make this project more robust.

* Dockerize the backend
* Add Logger for frontend and backend
* Add more log statements for frontend and backend
* Add exception handlers for frontend and backend
* Add unit tests for backend
* Add security layer for backend
* Add proper CORS handling 