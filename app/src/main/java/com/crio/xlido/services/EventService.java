package com.crio.xlido.services;

import java.util.Optional;
import com.crio.xlido.entities.Event;
import com.crio.xlido.entities.User;
import com.crio.xlido.repositories.IEventRepository;
import com.crio.xlido.repositories.IUserRepository;
import com.crio.xlido.repositories.UserRepository;


// public class EventService {
//     private final IEventRepository eventRepository ;
//     // private UserRepository userRepository = new UserRepository();
//     // private UserService userService = new UserService(userRepository);

//     public EventService(IEventRepository eventRepository){
//         this.eventRepository = eventRepository;
//     }
   
  
// public Event createEvent(Long organizerId , String title){

//  Event newEvent = new Event(organizerId , title);
//  Event savedEvent = eventRepository.save(newEvent);
// return savedEvent;
// }

// //Delete Event

// public void deleteEvent(Long eventId , Long userId){
// eventRepository.delete(eventId);
// }

// }

public class EventService {
    private final IUserRepository userRepository;
    private final IEventRepository eventRepository;
    public EventService(IUserRepository userRepository , IEventRepository eventRepository){
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public Event createEvent(Long organizerId , String title){

        Event newEvent = new Event(organizerId , title);
        Event savedEvent = eventRepository.save(newEvent);
       return savedEvent;
       }



    public void deleteEvent(Long id , Long userId){
        if(eventRepository.findByEventId(id).isEmpty()){
            throw new RuntimeException("Event with an id " + id + " does not exist");
        }

        if(userRepository.findById(userId).isEmpty()){
            throw new RuntimeException("User with an id " + userId + " does not exist");
        }

        if(!eventRepository.findByEventId(id).get().getUserId().equals(userId)){
            throw new RuntimeException("User with an id " + userId +" is not a organizer of Event with an id " + id);
        }

             eventRepository.delete(id);
             System.out.println("EVENT_DELETED " + id);
    }
}
