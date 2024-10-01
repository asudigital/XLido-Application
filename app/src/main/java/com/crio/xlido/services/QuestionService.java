package com.crio.xlido.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import com.crio.xlido.entities.Event;
import com.crio.xlido.entities.Question;
import com.crio.xlido.entities.Reply;
import com.crio.xlido.repositories.IEventRepository;
import com.crio.xlido.repositories.IQuestionRepository;
import com.crio.xlido.repositories.IUserRepository;

public class QuestionService {
    
    private final IQuestionRepository questionRepository;
    private final IUserRepository userRepository;
    private final IEventRepository eventRepository;

    public QuestionService(IQuestionRepository questionRepository ,IUserRepository userRepository ,IEventRepository eventRepository){
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

  

     // Add a new question to an event
     public void addQuestion(String content, Long userId, Long eventId) {
        // Validate if the event exists
        Optional<Event> event = eventRepository.findByEventId(eventId);
        if (event.isEmpty()) {          
            throw new RuntimeException("Event with an id " + eventId + " does not exist");
        }

        // Validate if the user exists
        if (!userRepository.userIdExistOrNot(userId)) {
            throw new RuntimeException("User with an id " + userId + " does not exist");
        }

        // Create and save the new question
        Question newQuestion = new Question(eventId, userId, content);
        Question savedQuestion = questionRepository.save(newQuestion);
        
        // AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        // saving the saved question in the  event
        Event currentEvent = event.get();
        currentEvent.getQuestions().add(savedQuestion);

        System.out.println("Question ID: " + savedQuestion.getQuestionId());
    }


    public void deleteQuestion(Long questionId, Long userId) throws Exception {
        // Check if user exists
        if (!userRepository.userIdExistOrNot(userId)) {
            throw new Exception("User with an id " + userId + " does not exist");
        }

        // Check if the question exists
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isEmpty()) {
            throw new Exception("Question with an id " + questionId + " does not exist");
        }

        Question question = questionOptional.get();
        
        // Check if the user is the author of the question
        if (!question.getUserId().equals(userId)) {
            throw new Exception("User with an id " + userId + " is not an author of question with an id " + questionId);
        }

        // If checks pass, delete the question
        questionRepository.delete(questionId);
        System.out.println("QUESTION_DELETED " + questionId);
    }

    public void upvoteQuestion(Long questionId , Long userId) throws Exception{
        
          // Check if user exists
          if (!userRepository.userIdExistOrNot(userId)) {
            throw new Exception("User with an id " + userId + " does not exist");
        }

            // Check if the question exists
            Optional<Question> questionOptional = questionRepository.findById(questionId);
            if (questionOptional.isEmpty()) {
                throw new Exception("Question with an id " + questionId + " does not exist");
            }

            //Check user with an Id , have already upvoted or not 
            Question question = questionOptional.get();
         
              // Check if the user has already upvoted the question
        if (question.getUpvotedUserIds().contains(userId)) {
            throw new RuntimeException("User with an id " + userId + " has already upvoted a question with an id " + questionId);
        }

         // Add the user to the set of upvoted users
         question.getUpvotedUserIds().add(userId);
         System.out.println("QUESTION_UPVOTED " + questionId);

    }

    public void replyToQuestion(String replyContent, Long questionId, Long userId) throws Exception {
         // Check if user exists
         if (!userRepository.userIdExistOrNot(userId)) {
            throw new Exception("User with an id " + userId + " does not exist");
        }

          // Check if the question exists
          Optional<Question> questionOptional = questionRepository.findById(questionId);
          if (questionOptional.isEmpty()) {
              throw new Exception("Question with an id " + questionId + " does not exist");
          }

          
          Question question = questionOptional.get();

          Long replyId = (long) (question.getReplies().size() + 1); // Generate a new reply ID
          Reply reply = new Reply(replyId, userId, replyContent);
          question.getReplies().add(reply); // Add the reply to the question

    //  Save the updated question
        questionRepository.updatQuestion(questionId , question);    // Ensure you have a save method in your repository

        System.out.println("REPLY_ADDED");
    }

    public void listOfQuestions(Long eventId , String sortBy){

            // Validate if the event exists
            Optional<Event> optionalEvent = eventRepository.findByEventId(eventId);
            if (optionalEvent.isEmpty()) {          
                throw new RuntimeException("Event with an id " + eventId + " does not exist");
            }

   
       Event event = optionalEvent.get();
       List<Question> questions = event.getQuestions();

      if(sortBy.equalsIgnoreCase("POPULAR")){
        Collections.sort(questions , new PopularityBasedSorting());
      }
      else if(sortBy.equalsIgnoreCase("RECENT")){
        Collections.sort(questions , new QuestionIdBasedSorting());

           // Reverse the order of questions
    //   Collections.reverse(questions); // Reverse the insertion order 
       
      }
  

        // Print the sorted questions
    for (Question question : questions) {
        System.out.println("Question ID: " + question.getQuestionId());
        System.out.println("Content: " + question.getContent());
        System.out.println("Votes: " + question.getUpvotedUserIds().size());
        System.out.println("Replies:");

        for (Reply reply : question.getReplies()) {
            System.out.println("  - User " + reply.getUserId() + ": " + reply.getContent());
        }
        
        System.out.println(); // For a blank line between questions
    }

    }

}

//Sorting based on popularity(upvote count)
class PopularityBasedSorting implements Comparator<Question>{

    @Override
    public int compare(Question q1, Question q2) {
        
        return Integer.compare(q2.getUpvotedUserIds().size(), q1.getUpvotedUserIds().size());
    }
}

// sorting based on recency(timeStamp)

class QuestionIdBasedSorting implements Comparator<Question>{

    @Override
    public int compare(Question q1, Question q2) {
       
        return Long.compare(q2.getQuestionId(), q1.getQuestionId());
    }

}
