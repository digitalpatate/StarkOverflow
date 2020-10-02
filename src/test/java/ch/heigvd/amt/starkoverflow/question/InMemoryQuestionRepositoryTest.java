package ch.heigvd.amt.starkoverflow.question;

import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory.InMemoryQuestionRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryQuestionRepositoryTest {

    private User contextUser;
    private InMemoryQuestionRepository questionRepository;

    @BeforeEach
    public void setup(){
        this.contextUser = new User("test@test.com","1234","sss","John", "Dow");
        this.questionRepository = new InMemoryQuestionRepository();
    }

    @Test
    public void saveShouldReturnSameObject(){
        Question question = new Question("Title","Content");
        question.setAuthor(this.contextUser);
        Question savedQuestion = this.questionRepository.save(question);
        //ArrayList<Question> savedQuestion = (ArrayList<Question>) questionRepository.findAll();
        assertEquals(savedQuestion,question);
    }
    @Test
    public void findByIdShouldReturnAnObject(){
        Question question = new Question("Title","Content");
        question.setAuthor(this.contextUser);
        Question savedQuestion = this.questionRepository.save(question);
        assertTrue(this.questionRepository.findById(savedQuestion.getId()).isPresent());
    }

    @Test
    public void afterRemoveFindShoudReturnEmptyOptional(){
        Question question = new Question("Title","Content");
        question.setAuthor(this.contextUser);
        Question savedQuestion = this.questionRepository.save(question);

        this.questionRepository.remove(savedQuestion.getId());


        assertTrue(this.questionRepository.findById(savedQuestion.getId()).isEmpty());

    }

}