package com.crio.xlido.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.crio.xlido.entities.Question;

public class QuestionRepository implements IQuestionRepository {
    
 // In-memory storage for questions, key is questionId
 private final Map<Long, Question> storage = new HashMap<>();
 private AtomicLong idCounter = new AtomicLong(0);


    @Override
    public Question save(Question question) {       
      Long questionId = idCounter.incrementAndGet();
      //Adding timestamp
      Question newQuestion = new Question(questionId, question.getEventId(), question.getUserId(),
       question.getContent(),System.currentTimeMillis());
      storage.put(questionId, newQuestion);
      return newQuestion;
    }


    @Override
    public Optional<Question> findById(Long questionId) {
        return Optional.ofNullable(storage.get(questionId));
    }


    @Override
    public void delete(Long questionId) {
       // Remove the question from storage
       storage.remove(questionId);     
    }

    @Override
    public void updatQuestion(Long questionId, Question question) {
      storage.put(questionId, question);   
    }
    
}
