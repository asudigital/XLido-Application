package com.crio.xlido.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.crio.xlido.entities.Event;

public class EventRepository implements IEventRepository{
    //This stores key as eventId , and value as as Event
    private  final Map<Long, Event> storage = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(0);

    @Override
    public Event save(Event event) {
        Event eventObject = new Event(idCounter.incrementAndGet(), event);
        storage.putIfAbsent(eventObject.getEventId(), eventObject);
        return eventObject;
    }

    @Override
    public void delete(Long eventId) {
        storage.remove(eventId);
        
    }

    @Override
    public boolean isEventIdExist(Long eventId) {      
       return storage.containsKey(eventId);
    }

    @Override
    public Optional<Event> findByEventId(Long eventId) {
        // System.out.println(storage.get(eventId).getEventId() + " Event_id");
        // System.out.println(storage.get(eventId).getUserId() + " User_id");
      return Optional.ofNullable(storage.get(eventId));
    }

    
    
}


