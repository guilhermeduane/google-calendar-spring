package gcalendar.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import gcalendar.model.Evento;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static gcalendar.config.GCalendarConfig.getCredentials;

public class GcalendarDeleteEventService {


    private static final String APPLICATION_NAME = "Spring Google Calendar API";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();


    public void deleteEvent(Evento evento) throws GeneralSecurityException, IOException {

        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME).build();

        service.events().delete(evento.getCalendarId(), evento.getEventId()).execute();

    }


}
