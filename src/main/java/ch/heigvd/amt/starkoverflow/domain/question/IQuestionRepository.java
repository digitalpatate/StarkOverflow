package ch.heigvd.amt.starkoverflow.domain.question;

import ch.heigvd.amt.starkoverflow.application.question.QuestionQuery;
import ch.heigvd.amt.starkoverflow.domain.IRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Collection;

public interface IQuestionRepository extends IRepository<Question,QuestionId> {
    public Collection<Question> find(QuestionQuery query);
}
