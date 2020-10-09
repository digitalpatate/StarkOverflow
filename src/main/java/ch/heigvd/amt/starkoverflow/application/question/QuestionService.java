package ch.heigvd.amt.starkoverflow.application.question;

import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionDTO;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.user.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
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
        //User author = userRepository.findById(command.getUserId()).orElseThrow(RuntimeException::new);
        //question.setAuthor(author);
        return questionRepository.save(question);
    }

    public QuestionsDTO getQuestion(QuestionQuery query){
        Collection<Question> questions = questionRepository.find(query);

        List<QuestionDTO> questionsDTO = questions.stream().map(question -> QuestionDTO.builder().build()).collect(Collectors.toList());

        return QuestionsDTO.builder().questions(questionsDTO).build();
    }

    public QuestionsDTO getQuestions() {
        Collection<Question> questions = questionRepository.findAll();

        List<QuestionDTO> questionsDTO = questions
                .stream()
                .map(question -> QuestionDTO
                        .builder()
                        .title(question.getTitle())
                        .content(question.getContent())
                        .build())
                .collect(Collectors.toList());

        return QuestionsDTO.builder().questions(questionsDTO).build();
    }
}
