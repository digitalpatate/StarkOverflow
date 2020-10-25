package ch.heigvd.amt.starkoverflow.infrastructure.memory.comment;

import ch.heigvd.amt.starkoverflow.domain.comment.ICommentable;
import ch.heigvd.amt.starkoverflow.domain.comment.Comment;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory.InMemoryAnswerCommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryCommentRepositoryTest {

    private InMemoryAnswerCommentRepository commentRepository;
    private Comment comment;
    private ICommentable aQuestion;
    // private User commentator;
    private User commentableOwner;

    @BeforeEach
    public void setup(){
        this.commentRepository = new InMemoryAnswerCommentRepository();


        this.aQuestion = new Question("Very Important question","content", new UserId());
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