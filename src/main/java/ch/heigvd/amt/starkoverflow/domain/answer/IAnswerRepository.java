package ch.heigvd.amt.starkoverflow.domain.answer;

import ch.heigvd.amt.starkoverflow.application.Answer.AnswerQuery;
import ch.heigvd.amt.starkoverflow.domain.IRepository;

import java.util.Collection;

public interface IAnswerRepository extends IRepository<Answer, AnswerId> {
    Collection<Answer> find(AnswerQuery query);
}
