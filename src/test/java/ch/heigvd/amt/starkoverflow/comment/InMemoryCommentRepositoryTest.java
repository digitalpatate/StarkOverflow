package ch.heigvd.amt.starkoverflow.comment;

import ch.heigvd.amt.starkoverflow.domain.Commentable;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.comment.Comment;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory.InMemoryCommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryCommentRepositoryTest {

    private InMemoryCommentRepository commentRepository;
    private Comment comment;
    private Commentable aQuestion;
    // private User commentator;
    private User commentableOwner;

    @BeforeEach
    public void setup(){
        this.commentRepository = new InMemoryCommentRepository();


        this.aQuestion = new Question("Very Important question","content");
        ((Question)this.aQuestion).setAuthor(new UserId());

        this.comment= new Comment("This is a good comment !");
    }


    @Test
    public void saveShouldReturnSameObject(){
        Comment savedComment = commentRepository.save(this.comment);

        assertEquals(this.comment,savedComment);
    }
    @Test
    public void findByIdShouldReturnAnObject(){
        commentRepository.save(this.comment);
        assertTrue(this.commentRepository.findById(comment.getId()).isPresent());

    }

    @Test
    public void afterRemoveFindShoudReturnEmptyOptional(){
        commentRepository.save(this.comment);
        commentRepository.remove(comment.getId());

        assertTrue(this.commentRepository.findById(comment.getId()).isEmpty());

    }

}