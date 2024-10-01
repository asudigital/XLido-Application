package com.crio.xlido.repositories;

import java.util.Optional;
import com.crio.xlido.entities.Question;

public interface IQuestionRepository {

    Question save(Question question);
    Optional<Question> findById(Long questionId);
    void delete(Long questionId);
    void updatQuestion(Long questionId , Question question);
}
