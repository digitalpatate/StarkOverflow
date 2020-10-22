package ch.heigvd.amt.starkoverflow.application.question;

import ch.heigvd.amt.starkoverflow.application.Answer.dto.AnswerDTO;
import ch.heigvd.amt.starkoverflow.application.Answer.dto.AnswersDTO;
import ch.heigvd.amt.starkoverflow.application.Comment.dto.CommentDTO;
import ch.heigvd.amt.starkoverflow.application.Comment.dto.CommentsDTO;
import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagDTO;
import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagsDTO;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionDTO;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.comment.Comment;
import ch.heigvd.amt.starkoverflow.domain.comment.IAnswerCommentRepository;
import ch.heigvd.amt.starkoverflow.domain.comment.IQuestionCommentRepository;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.user.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.domain.vote.IAnswerVoteRepository;
import ch.heigvd.amt.starkoverflow.domain.vote.IQuestionVoteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.NotFoundException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Named("QuestionService")
@ApplicationScoped
public class QuestionService {

    @Inject @Named("JdbcQuestionRepository")
    private IQuestionRepository questionRepository;

    @Inject @Named("JdbcUserRepository")
    private IUserRepository userRepository;

    @Inject @Named("JdbcAnswerVoteRepository")
    private IAnswerVoteRepository answerVoteRepository;

    @Inject @Named("JdbcQuestionVoteRepository")
    private IQuestionVoteRepository questionVoteRepository;

    @Inject @Named("JdbcQuestionCommentRepository")
    private IQuestionCommentRepository questionCommentRepository;

    @Inject @Named("JdbcAnswerCommentRepository")
    private IAnswerCommentRepository answerCommentRepository;

    public Question createQuestion(CreateQuestionCommand command) {
        Question question = command.createEntity();

        question = questionRepository.save(question);

        for(Tag tag : command.getTags()) {
            questionRepository.addTag(question.getId(), tag.getId());
        }

        return question;
    }

    /*public QuestionsDTO getQuestion(QuestionQuery query){
        Collection<Question> questions = questionRepository.find(query);

        List<QuestionDTO> questionsDTO = questions.stream().map(question -> QuestionDTO.builder().build()).collect(Collectors.toList());

        return QuestionsDTO.builder().questions(questionsDTO).build();
    }*/

    public QuestionDTO getQuestion(QuestionId id, UserId viewer) {
        Optional<Question> oQuestion = questionRepository.findById(id);

        Question question = oQuestion.orElseThrow(()-> new NotFoundException("No question found with this id"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH'h'mm dd/MM/yyyy");

        TagsDTO tags = getQuestionTags(question.getId());
        AnswersDTO answers = getQuestionAnswers(question.getId(), viewer);
        CommentsDTO comments = getQuestionComments(question.getId());
        UserDTO userDTO = userRepository.findById(question.getAuthor()).map(
                user -> UserDTO.builder()
                .id(user.getId().asString())
                .profilePicture(user.getProfilePictureURL())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .email(user.getEmail())
                .build()
        ).orElseThrow(() -> new NotFoundException("Question author " + question.getAuthor().asString() + " not found!"));

        String acceptedAnswerId = questionRepository.getAcceptedAnswer(question.getId())
                .map(answer -> answer.getId().asString())
                .orElse("");

        QuestionDTO questionDTO = QuestionDTO.builder()
                .id(question.getId().asString())
                .title(question.getTitle())
                .content(question.getContent())
                .creationDate(dateFormat.format(question.getCreationDate()))
                .tags(tags)
                .answers(answers)
                .acceptedAnswerId(acceptedAnswerId)
                .user(userDTO)
                .comments(comments)
                .nbVotes(questionVoteRepository.getNbVotesOfQuestion(question.getId()))
                .voted(viewer != null && questionVoteRepository.userVoteOnQuestion(viewer, question.getId()) != null)
                .build();

        return questionDTO;
    }

    public TagsDTO getQuestionTags(QuestionId questionId) {
        Collection<Tag> tags = questionRepository.getQuestionTags(questionId);

        List<TagDTO> tagsDTO = tags
                .stream()
                .map(tag -> TagDTO.builder()
                        .name(tag.getName())
                        .color(tag.getColor())
                        .build())
                .collect(Collectors.toList());

        return TagsDTO.builder().tags(tagsDTO).build();
    }

    public AnswersDTO getQuestionAnswers(QuestionId questionId, UserId viewer) {
        Collection<Answer> answers = questionRepository.getQuestionAnswers(questionId);

        List<AnswerDTO> answersDTO = answers
                .stream()
                .map(answer -> AnswerDTO.builder()
                        .id(answer.getId().asString())
                        .content(answer.getContent())
                        .voted(viewer != null && answerVoteRepository.userVoteOnAnswer(viewer, answer.getId()) != null)
                        .nbVotes(answerVoteRepository.getNbVotesOfAnswer(answer.getId()))
                        .user(userRepository.findById(answer.getUserId())
                                .map(user -> UserDTO.builder()
                                        .username(user.getUsername())
                                        .email(user.getEmail())
                                        .lastname(user.getLastname())
                                        .firstname(user.getLastname())
                                        .profilePicture(user.getProfilePictureURL())
                                        .id(user.getId().asString())
                                        .build()
                                ).orElseThrow(() -> new NotFoundException("Answer user " + answer.getUserId().asString() + " not found!"))
                        )
                        .comments(getAnswerComments(answer.getId()))
                        .build())
                .collect(Collectors.toList());

        return AnswersDTO.builder().answers(answersDTO).build();
    }

    public CommentsDTO getQuestionComments(QuestionId questionId) {
        Collection<Comment> comments = questionCommentRepository.getCommentsOnQuestion(questionId);

        List<CommentDTO> commentDTO = comments
                .stream()
                .map(comment -> CommentDTO.builder()
                        .id(comment.getId().asString())
                        .content(comment.getContent())
                        .user(userRepository.findById(comment.getAuthor())
                                .map(user -> UserDTO.builder()
                                        .username(user.getUsername())
                                        .email(user.getEmail())
                                        .lastname(user.getLastname())
                                        .firstname(user.getLastname())
                                        .profilePicture(user.getProfilePictureURL())
                                        .id(user.getId().asString())
                                        .build()
                                ).orElseThrow(() -> new NotFoundException("Comment user " + comment.getAuthor().asString() + " not found!"))
                        )
                        .build())
                .collect(Collectors.toList());

        return CommentsDTO.builder().comments(commentDTO).build();
    }

    public CommentsDTO getAnswerComments(AnswerId answerId) {
        Collection<Comment> comments = answerCommentRepository.getCommentsOnAnswer(answerId);

        List<CommentDTO> commentDTO = comments
                .stream()
                .map(comment -> CommentDTO.builder()
                        .id(comment.getId().asString())
                        .content(comment.getContent())
                        .user(userRepository.findById(comment.getAuthor())
                                .map(user -> UserDTO.builder()
                                        .username(user.getUsername())
                                        .email(user.getEmail())
                                        .lastname(user.getLastname())
                                        .firstname(user.getLastname())
                                        .profilePicture(user.getProfilePictureURL())
                                        .id(user.getId().asString())
                                        .build()
                                ).orElseThrow(() -> new NotFoundException("Comment user " + comment.getAuthor().asString() + " not found!"))
                        )
                        .build())
                .collect(Collectors.toList());

        return CommentsDTO.builder().comments(commentDTO).build();
    }

    public QuestionsDTO getQuestions() {
        Collection<Question> questions = questionRepository.findAll();

        List<QuestionDTO> questionsDTO = questions
                .stream()
                .map(question -> QuestionDTO.builder()
                        .id(question.getId().asString())
                        .title(question.getTitle())
                        .content(question.getContent())
                        .tags(getQuestionTags(question.getId()))
                        .build())
                .collect(Collectors.toList());

        return QuestionsDTO.builder().questions(questionsDTO).build();
    }

    public QuestionsDTO getQuestionsByAuthor(String authorId) {
        Collection<Question> questions = questionRepository.findByAuthor(authorId);

        List<QuestionDTO> questionsDTO = questions
                .stream()
                .map(question -> QuestionDTO.builder()
                        .id(question.getId().asString())
                        .title(question.getTitle())
                        .content(question.getContent())
                        .tags(getQuestionTags(question.getId()))
                        .build())
                .collect(Collectors.toList());

        return QuestionsDTO.builder().questions(questionsDTO).build();
    }
}
