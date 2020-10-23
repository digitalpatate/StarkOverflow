package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.application.Answer.AnswerQuery;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.answer.IAnswerRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("InMemoryAnswerRepository")
public class InMemoryAnswerRepository extends InMemoryRepository<Answer,AnswerId> implements IAnswerRepository {

    @Override
    public Collection<Answer> find(AnswerQuery query) {
        return null;
    }

    @Override
    public Optional<Question> getAnswerQuestion(AnswerId id) {
        return Optional.empty();
    }

    @Override
    public void accept(AnswerId answerId) {

    }

    @Override
    public Collection<Answer> getByQuestionId(QuestionId questionId) {
        return null;
    }

}
