package ch.heigvd.amt.starkoverflow.application.Answer;

import ch.heigvd.amt.starkoverflow.application.Answer.dto.AnswerDTO;
import ch.heigvd.amt.starkoverflow.application.Answer.dto.AnswersDTO;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.answer.IAnswerRepository;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.UserId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.NotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Named("AnswerService")
@ApplicationScoped
public class AnswerService {

    @Inject @Named("JdbcAnswerRepository")
    private IAnswerRepository answerRepository;

    @Inject @Named("JdbcQuestionRepository")
    private IQuestionRepository questionRepository;

    public void createAnswer(CreateAnswerCommand command){
        Answer answer = command.createEntity();
        answerRepository.save(answer);
    }

    public AnswersDTO getAnswers(AnswerQuery query){
        Collection<Answer> answers = answerRepository.find(query);

        List<AnswerDTO> answersDTO = answers.stream().map(answer -> AnswerDTO.builder().build()).collect(Collectors.toList());

        return AnswersDTO.builder().answers(answersDTO).build();
    }

    public void acceptAnswer(AcceptAnswerCommand command) {
        Question question = answerRepository.getAnswerQuestion(new AnswerId(command.getAnswerId()))
                .map(q -> (Question) q)
                .orElseThrow(() -> new NotFoundException("Answer's question not found"));

        UserId currentUser = new UserId(command.getUserId());

        // If user is not the question's author, accepting answer is forbidden
        if(!currentUser.equals(question.getAuthor())) {
            throw new RuntimeException("Only question author can accept its answers!");
        }

        // Only one answer can be accepted
        if(questionRepository.hasAcceptedAnswer(question.getId())) {
            throw new RuntimeException("Only question's answer can be accepted!");
        }

        answerRepository.accept(new AnswerId(command.getAnswerId()));
    }
}
