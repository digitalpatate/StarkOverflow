package ch.heigvd.amt.starkoverflow.domain.answer;

import ch.heigvd.amt.starkoverflow.application.Answer.AnswerQuery;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.domain.IRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;

import java.util.Collection;
import java.util.Optional;

public interface IAnswerRepository extends IRepository<Answer, AnswerId> {
    Collection<Answer> find(AnswerQuery query);

    Optional<Question> getAnswerQuestion(AnswerId id);

    void accept(AnswerId answerId);
}
