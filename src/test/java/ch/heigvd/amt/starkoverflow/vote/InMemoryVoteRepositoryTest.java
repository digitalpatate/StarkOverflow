package ch.heigvd.amt.starkoverflow.vote;

import ch.heigvd.amt.starkoverflow.domain.Commentable;
import ch.heigvd.amt.starkoverflow.domain.Votable;
import ch.heigvd.amt.starkoverflow.domain.comment.Comment;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.vote.Vote;
import ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory.InMemoryCommentRepository;
import ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory.InMemoryVoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryVoteRepositoryTest {

    private InMemoryVoteRepository voteRepository;
    private Vote vote;
    private Votable aQuestion;
    // private User commentator;
    private User voteUser;

    @BeforeEach
    public void setup(){
        this.voteRepository = new InMemoryVoteRepository();

        //this.commentator = new User("commentator","test","test","test","test");
        this.voteUser = new User("michel", "answerer","test","test","test","test");

        this.aQuestion = new Question("Very Important question","content");
        ((Question)this.aQuestion).setAuthor(this.voteUser);

        this.vote= new Vote();
    }


    @Test
    public void saveShouldReturnSameObject(){
        Vote savedVote = voteRepository.save(this.vote);

        assertEquals(this.vote,savedVote);
    }
    @Test
    public void findByIdShouldReturnAnObject(){
        voteRepository.save(this.vote);
        assertTrue(this.voteRepository.findById(vote.getId()).isPresent());

    }

    @Test
    public void afterRemoveFindShoudReturnEmptyOptional(){
        voteRepository.save(this.vote);
        voteRepository.remove(vote.getId());

        assertTrue(this.voteRepository.findById(vote.getId()).isEmpty());

    }

}