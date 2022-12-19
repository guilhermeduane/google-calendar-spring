package gcalendar.repository;


import gcalendar.model.Evento;
import org.springframework.data.repository.CrudRepository;


public interface EventoRepository
         extends CrudRepository<Evento, Long> {


   Evento findByEventId(String eventId);

}
