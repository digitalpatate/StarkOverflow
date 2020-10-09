package ch.heigvd.amt.starkoverflow.question;

import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory.InMemoryQuestionRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryQuestionRepositoryTest {

    private InMemoryQuestionRepository questionRepository;

    @BeforeEach
    public void setup(){
        this.questionRepository = new InMemoryQuestionRepository();
    }

    @Test
    public void saveShouldReturnSameObject(){
        Question question = new Question("Title","Content", new UserId());
        question.setAuthor(new UserId());
        Question savedQuestion = this.questionRepository.save(question);
        //ArrayList<Question> savedQuestion = (ArrayList<Question>) questionRepository.findAll();
        assertEquals(savedQuestion,question);
    }
    @Test
    public void findByIdShouldReturnAnObject(){
        Question question = new Question("Title","Content",new UserId());
        question.setAuthor(new UserId());
        Question savedQuestion = this.questionRepository.save(question);
        assertTrue(this.questionRepository.findById(savedQuestion.getId()).isPresent());
    }

    @Test
    public void afterRemoveFindShoudReturnEmptyOptional(){
        Question question = new Question("Title","Content", new UserId());
        question.setAuthor(new UserId());
        Question savedQuestion = this.questionRepository.save(question);

        this.questionRepository.remove(savedQuestion.getId());


        assertTrue(this.questionRepository.findById(savedQuestion.getId()).isEmpty());

    }

}