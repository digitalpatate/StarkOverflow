package ch.heigvd.amt.starkoverflow.application.question;

import ch.heigvd.amt.starkoverflow.application.Answer.dto.AnswerDTO;
import ch.heigvd.amt.starkoverflow.application.Answer.dto.AnswersDTO;
import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagDTO;
import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagsDTO;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionDTO;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.user.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.user.User;
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

    public Question createQuestion(CreateQuestionCommand command) {
        Question question = command.createEntity();

        question = questionRepository.save(question);

        for(Tag tag : command.getTags()) {
            questionRepository.addTag(question.getId(), tag.getId());
        }

        return question;
    }

    public QuestionsDTO getQuestion(QuestionQuery query){
        Collection<Question> questions = questionRepository.find(query);

        List<QuestionDTO> questionsDTO = questions.stream().map(question -> QuestionDTO.builder().build()).collect(Collectors.toList());

        return QuestionsDTO.builder().questions(questionsDTO).build();
    }

    public QuestionDTO getQuestion(QuestionId id) {
        Optional<Question> oQuestion = questionRepository.findById(id);

        Question question = oQuestion.orElseThrow(()-> new NotFoundException("No question found with this id"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH'h'mm dd/MM/yyyy");

        TagsDTO tags = getQuestionTags(question.getId());
        AnswersDTO answers = getQuestionAnswers(question.getId());

        QuestionDTO questionDTO = QuestionDTO.builder()
                .id(question.getId().asString())
                .title(question.getTitle())
                .content(question.getContent())
                .creationDate(dateFormat.format(question.getCreationDate()))
                .tags(tags)
                .answers(answers)
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

    public AnswersDTO getQuestionAnswers(QuestionId questionId) {
        Collection<Answer> answers = questionRepository.getQuestionAnswers(questionId);

        List<AnswerDTO> answersDTO = answers
                .stream()
                .map(answer -> AnswerDTO.builder()
                        .content(answer.getContent())
                        .build())
                .collect(Collectors.toList());

        return AnswersDTO.builder().answers(answersDTO).build();
    }

    public QuestionsDTO getQuestions() {
        Collection<Question> questions = questionRepository.findAll();

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH'h'mm dd/MM/yyyy");

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
