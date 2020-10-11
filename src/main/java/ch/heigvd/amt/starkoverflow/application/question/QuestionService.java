package ch.heigvd.amt.starkoverflow.application.question;

import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionDTO;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
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

    @Inject
    @Named("JdbcQuestionRepository")
    private IQuestionRepository questionRepository;

    public Question createQuestion(CreateQuestionCommand command) {
        Question question = command.createEntity();

        return questionRepository.save(question);
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

        QuestionDTO questionDTO = QuestionDTO.builder()
                .id(question.getId().asString())
                .title(question.getTitle())
                .content(question.getContent())
                .creationDate(dateFormat.format(question.getCreationDate()))
                .build();

        return questionDTO;
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
                        .build())
                .collect(Collectors.toList());

        return QuestionsDTO.builder().questions(questionsDTO).build();
    }
}
