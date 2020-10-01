package ch.heigvd.amt.starkoverflow.application.Answer;

import ch.heigvd.amt.starkoverflow.application.Answer.dto.AnswerDTO;
import ch.heigvd.amt.starkoverflow.application.Answer.dto.AnswersDTO;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionDTO;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.answer.IAnswerRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AnswerService {
    private IAnswerRepository answerRepository;

    public AnswerService(IAnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public void createAnswer(CreateAnswerCommand command){
        Answer answer = command.createEntity();
        answerRepository.save(answer);
    }

    public AnswersDTO getAnswers(AnswerQuery query){
        Collection<Answer> answers = answerRepository.find(query);

        List<AnswerDTO> answersDTO = answers.stream().map(answer -> AnswerDTO.builder().build()).collect(Collectors.toList());

        return AnswersDTO.builder().answers(answersDTO).build();
    }
}
