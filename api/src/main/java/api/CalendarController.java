package api;



import calendar.service.GCalendarAddEventService;
import calendar.service.GCalendarShowEventsService;
import calendar.service.GcalendarDeleteEventService;
import model.Evento;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import repository.EventoRepository;

import java.io.IOException;
import java.security.GeneralSecurityException;


@RestController
@RequestMapping(path="/api/calendar", produces="application/json")
@CrossOrigin(origins="http://localhost:8080")
public class CalendarController {

    GCalendarShowEventsService gCalendarShowEventsService= new GCalendarShowEventsService();
    GCalendarAddEventService gCalendarAddEventService = new GCalendarAddEventService();

    GcalendarDeleteEventService gcalendarDeleteEventService = new GcalendarDeleteEventService();
    private EventoRepository repo;

    public CalendarController(EventoRepository repo){
        this.repo = repo;
    }

    @GetMapping
    public void allUpcomingEventsApiDirect() throws GeneralSecurityException, IOException {
        gCalendarShowEventsService.ShowEvents();
    }

    @GetMapping(value = "/all",produces = "application/json")
    public Iterable<Evento> allEventsInDatabase(){
        return repo.findAll();
    }

    @DeleteMapping("/delete/{eventId}")
    public void deleteEvent(@PathVariable("eventId") String eventId) throws GeneralSecurityException, IOException {
        Evento ev = repo.findByEventId(eventId);
        gcalendarDeleteEventService.deleteEvent(ev);
        try {
            repo.delete(ev);
        }catch (EmptyResultDataAccessException e ){}
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Evento addEvent(@RequestBody Evento evento) throws GeneralSecurityException, IOException {
        String eventId;
        eventId = gCalendarAddEventService.addEvent(evento);
        evento.setEventId(eventId);
        return repo.save(evento);
    }

}
