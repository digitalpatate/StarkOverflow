package ch.heigvd.amt.starkoverflow.domain.question;

import ch.heigvd.amt.starkoverflow.domain.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest {
    Question question;
    UserId userId;
    QuestionId questionId;
    Date date;
    private final String
    TITLE = "Null Pointer Exception is plaguing my code",
    CONTENT = "I am doing a project in which I have to create" +
            " a circular queue and then print it. The problem is" +
            " that I've been receiving the null pointer exception" +
            " error from these statements: the return statement ... ";

    @BeforeEach
    public void setup() {
        this.date = new Date();
        this.userId = new UserId();
        this.questionId = new QuestionId();
        this.question = new  Question(
                this.questionId,
                TITLE,
                CONTENT,
                this.date,
                this.userId);
    }

    @Test
    public void questionShouldNotBeNull(){
        assertNotNull(this.question);
    }


    @Test
    public void oneShouldBeAbleToGetInfoFromQuestionObject(){
        assertEquals(TITLE, this.question.getTitle());
        assertEquals(CONTENT, this.question.getContent());
        assertEquals(this.date, this.question.getCreationDate());
        assertEquals(this.userId, this.question.getAuthor());
        assertEquals(this.questionId, this.question.getId());
    }
}
