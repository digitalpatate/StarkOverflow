package ch.heigvd.amt.starkoverflow.application;

import ch.heigvd.amt.starkoverflow.application.Answer.AnswerService;
import ch.heigvd.amt.starkoverflow.application.Comment.CommentService;
import ch.heigvd.amt.starkoverflow.application.Tag.TagService;
import ch.heigvd.amt.starkoverflow.application.User.UserService;
import ch.heigvd.amt.starkoverflow.application.Vote.VoteService;
import ch.heigvd.amt.starkoverflow.application.question.QuestionService;
import ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory.*;
import lombok.Data;
import lombok.Getter;

public class ServiceRegistry {

    private static ServiceRegistry instance;

    private static AnswerService answerService;
    private static CommentService commentService;
    private static QuestionService questionService;
    private static TagService tagService;
    private static UserService userService;
    private static VoteService voteService;

    public static ServiceRegistry getServiceRegistry(){
        if(instance == null){
            instance = new ServiceRegistry();
        }
        return instance;
    }

    private ServiceRegistry(){
        instance = this;
        //Autoloading ? Factory
        InMemoryAnswerRepository inMemoryAnswerRepository = new InMemoryAnswerRepository();
        InMemoryCommentRepository inMemoryCommentRepository = new InMemoryCommentRepository();
        InMemoryQuestionRepository inMemoryQuestionRepository = new InMemoryQuestionRepository();
        InMemoryTagRepository inMemoryTagRepository = new InMemoryTagRepository();
        InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();
        InMemoryVoteRepository inMemoryVoteRepository = new InMemoryVoteRepository();
        answerService = new AnswerService(inMemoryAnswerRepository);
        commentService = new CommentService(inMemoryCommentRepository);
        questionService = new QuestionService(inMemoryQuestionRepository);
        tagService=new TagService(inMemoryTagRepository);
        userService = new UserService(inMemoryUserRepository);
        voteService = new VoteService(inMemoryVoteRepository);
    }

    public AnswerService getAnswerService() {
        return answerService;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public QuestionService getQuestionService() {
        return questionService;
    }

    public TagService getTagService() {
        return tagService;
    }

    public UserService getUserService() {
        return userService;
    }

    public VoteService getVoteService() {
        return voteService;
    }
}
