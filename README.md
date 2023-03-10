# Google Calendar Spring API
Example of using the google calendar API with Spring Boot, MySQL and Docker Compose.
## Enable the API and authorize credentials for a desktop application

* Follow the steps: https://developers.google.com/calendar/api/quickstart/java
* Copy generated credetials.json file to directory : /calendar/src/main/resources/

## 🚀 Run Application
To run application just run Docker Compose:
```
docker compose up --build
```
or
```
docker-compose up --build
```
You can debug app on container using port 5005.

## 💻 Test Spring API 
Use the collection file gcalendar.postman_collection.json to test the application

## 😄 TODO 
TODO Tasks :
* Adjust the Event template to receive the daily frequency of the event, attendee's email and reminders (email and pop-up)
* Synchronize the database with the data obtained from the API
* Add Google Drive attachments to events
* Create new endpoints to Spring App
