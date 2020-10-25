package ch.heigvd.amt.starkoverflow.infrastructure.memory.vote;

import ch.heigvd.amt.starkoverflow.domain.vote.IVotable;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.domain.vote.Vote;
import ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory.InMemoryAnswerVoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryVoteRepositoryTest {

    private InMemoryAnswerVoteRepository voteRepository;
    private Vote vote;
    private IVotable aQuestion;
    // private User commentator;
    private User voteUser;

    @BeforeEach
    public void setup(){
        this.voteRepository = new InMemoryAnswerVoteRepository();

        //this.commentator = new User(new UserId(),"commentator","test","test","test","test");
        this.voteUser = new User(new UserId(),"michel", "answerer","test","test","test","test");

        this.aQuestion = new Question("Very Important question","content", new UserId());
        ((Question)this.aQuestion).setAuthor(new UserId());

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
        Optional<Vote> oVote = this.voteRepository.findById(vote.getId());
        assertTrue(oVote.isPresent());
        assertEquals(this.vote,oVote.get());
    }

    @Test
    public void afterRemoveFindShoudReturnEmptyOptional(){
        voteRepository.save(this.vote);
        voteRepository.remove(vote.getId());

        assertTrue(this.voteRepository.findById(vote.getId()).isEmpty());

    }

}