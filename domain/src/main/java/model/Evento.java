package model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Evento {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  private String summary;
  private String location;
  private String description;
  private String startDateTime;
  private String timeZone;
  private String endDateTime;
// private String frequency;
// private int count;
// private String eventAttendeeEmail;
// private int eventReminderMinutes;
  private String calendarId;
  private String eventId;

}
