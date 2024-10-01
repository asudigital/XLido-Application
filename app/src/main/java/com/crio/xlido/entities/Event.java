package com.crio.xlido.entities;

import java.util.ArrayList;
import java.util.List;

public class Event {
    //userId is the organiser
   private Long userId;
   private Long eventId;
   private String title;
   private List<Question> questions = new ArrayList<>();

   //List to store this question for this event
 

public Event(Long userId , String title) {
    this.userId = userId;
    this.title = title;
}


public Event(Long userId, Long eventId, String title) {
    this.userId = userId;
    this.eventId = eventId;
    this.title = title;
}

public Event(Long eventId, Event event){
    this.eventId = eventId;
    this.userId = event.getUserId();
    this.title = event.getTitle();
}

// GETTERS and SETTERS
public Long getUserId() {
    return userId;
}

public void setUserId(Long userId) {
    this.userId = userId;
}

public Long getEventId() {
    return eventId;
}

public void setEventId(Long eventId) {
    this.eventId = eventId;
}

public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}


public List<Question> getQuestions() {
    return questions;
}


public void setQuestions(List<Question> questions) {
    this.questions = questions;
}

    
}
