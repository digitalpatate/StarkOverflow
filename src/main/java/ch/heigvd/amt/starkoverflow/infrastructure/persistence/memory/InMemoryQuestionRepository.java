package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.application.question.QuestionQuery;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Collection;


@ApplicationScoped
@Named("InMemoryQuestionRepository")
public class InMemoryQuestionRepository extends InMemoryRepository<Question, QuestionId> implements IQuestionRepository {
    @Override
    public Collection<Question> find(QuestionQuery query) {
        return null;
    }
}
