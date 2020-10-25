package ch.heigvd.amt.starkoverflow.infrastructure.memory.answer;

import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory.InMemoryAnswerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryAnswerRepositoryTest {

    private InMemoryAnswerRepository answerRepository;
    private Question question;
    private Answer answer;
    private User questioner;
    private User answerer;

    @BeforeEach
    public void setup(){
        this.answerRepository = new InMemoryAnswerRepository();

        this.questioner = new User(new UserId(),"michel", "questioner","test","test","test","test");
        this.answerer = new User(new UserId(),"michel", "answerer","test","test","test","test");
        this.question = new Question("title","content", new UserId());
        this.question.setAuthor(new UserId());
        this.answer= new Answer("NO");
    }


    @Test
    public void saveShouldReturnSameObject(){
        Answer savedAnswer = answerRepository.save(this.answer);

        assertEquals(this.answer,savedAnswer);
    }
    @Test
    public void findByIdShouldReturnAnObject(){
        answerRepository.save(this.answer);
        assertTrue(this.answerRepository.findById(answer.getId()).isPresent());

    }

    @Test
    public void afterRemoveFindShoudReturnEmptyOptional(){
        answerRepository.save(this.answer);
        answerRepository.remove(answer.getId());

        assertTrue(this.answerRepository.findById(answer.getId()).isEmpty());

    }

}