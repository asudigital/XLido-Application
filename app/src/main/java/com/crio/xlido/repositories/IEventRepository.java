package com.crio.xlido.repositories;

import java.util.Optional;
import com.crio.xlido.entities.Event;

public interface IEventRepository {
    Event save(Event event);
    void delete(Long eventId);
    boolean isEventIdExist(Long eventId);
    Optional<Event> findByEventId(Long eventId);
}
