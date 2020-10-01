package ch.heigvd.amt.starkoverflow;

import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory.InMemoryQuestionRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.enterprise.inject.Stereotype;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryQuestionRepositoryTest {

    private User contextUser;
    private InMemoryQuestionRepository questionRepository;

    @BeforeEach
    public void setup(){
        this.contextUser = new User("test@test.com","1234","sss","John", "Dow");
        this.questionRepository = new InMemoryQuestionRepository();
    }

    @Test
    public void createAQuestion(){

        Question question = new Question("Title","Content");

        question.setAuthor(this.contextUser);

        this.questionRepository.save(question);

        ArrayList<Question> savedQuestion = (ArrayList<Question>) questionRepository.findAll();

        assertEquals(savedQuestion.get(0),question);
    }

}