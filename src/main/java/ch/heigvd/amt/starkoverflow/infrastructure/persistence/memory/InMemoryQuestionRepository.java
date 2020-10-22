package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.application.question.QuestionQuery;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.tag.TagId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("InMemoryQuestionRepository")
public class InMemoryQuestionRepository extends InMemoryRepository<Question, QuestionId> implements IQuestionRepository {
    @Override
    public Collection<Question> find(QuestionQuery query) {
        return null;
    }

    @Override
    public void addTag(QuestionId questionId, TagId tagId) {

    }

    @Override
    public Collection<Tag> getQuestionTags(QuestionId questionId) {
        return null;
    }

    @Override
    public Collection<Answer> getQuestionAnswers(QuestionId questionId) {
        return null;
    }

    @Override
    public Collection<Question> findByAuthor(String authorId) {
        return null;
    }

    @Override
    public Collection<Question> findByTag(String authorId) {
        return null;
    }

    @Override
    public Optional<Answer> getAcceptedAnswer(QuestionId questionId) {
        return Optional.empty();
    }

    @Override
    public boolean hasAcceptedAnswer(QuestionId questionId) {
        return false;
    }
}
