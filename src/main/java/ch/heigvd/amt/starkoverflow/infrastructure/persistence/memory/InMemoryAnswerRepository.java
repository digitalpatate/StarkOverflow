package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.application.Answer.AnswerQuery;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.answer.IAnswerRepository;

import java.util.Collection;

public class InMemoryAnswerRepository extends InMemoryRepository<Answer,AnswerId> implements IAnswerRepository {

    @Override
    public Collection<Answer> find(AnswerQuery query) {
        return null;
    }
}
