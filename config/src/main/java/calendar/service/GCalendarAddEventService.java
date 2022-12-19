package calendar.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import model.Evento;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import static calendar.config.GCalendarConfig.getCredentials;

public class GCalendarAddEventService {

    private static final String APPLICATION_NAME = "Spring Google Calendar API";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

 public String addEvent(Evento evento) throws GeneralSecurityException, IOException {
     final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
     Calendar service =
             new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                     .setApplicationName(APPLICATION_NAME)
                     .build();
     Event event = new Event()
             .setSummary(evento.getSummary())
             .setLocation(evento.getLocation())
             .setDescription(evento.getDescription());

     DateTime startDateTime = new DateTime(evento.getStartDateTime());
     EventDateTime start = new EventDateTime()
             .setDateTime(startDateTime)
             .setTimeZone(evento.getTimeZone());
     event.setStart(start);

     DateTime endDateTime = new DateTime(evento.getEndDateTime());
     EventDateTime end = new EventDateTime()
             .setDateTime(endDateTime)
             .setTimeZone(evento.getTimeZone());
     event.setEnd(end);

     String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=1"};
     event.setRecurrence(Arrays.asList(recurrence));

     EventAttendee[] attendees = new EventAttendee[] {
             new EventAttendee().setEmail("lpage@example.com"),
             new EventAttendee().setEmail("sbrin@example.com"),
     };
     event.setAttendees(Arrays.asList(attendees));

     EventReminder[] reminderOverrides = new EventReminder[] {
             new EventReminder().setMethod("email").setMinutes(24 * 60),
             new EventReminder().setMethod("popup").setMinutes(10),
     };
     Event.Reminders reminders = new Event.Reminders()
             .setUseDefault(false)
             .setOverrides(Arrays.asList(reminderOverrides));
     event.setReminders(reminders);

     String calendarId = evento.getCalendarId();
     event = service.events().insert(calendarId, event).execute();

     System.out.printf("Event created: %s\n", event.getHtmlLink());

    return event.getId();
 }

}
