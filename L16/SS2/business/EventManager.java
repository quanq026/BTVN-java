package business;

import model.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private final List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public String getEventStatus(Event event) {
        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(event.getStartDate())) {
            return "Sắp tới";
        }

        if (now.isAfter(event.getEndDate())) {
            return "Đã qua";
        }

        return "Đang diễn ra";
    }
}
