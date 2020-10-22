package ch.heigvd.amt.starkoverflow.domain.question;

import ch.heigvd.amt.starkoverflow.application.question.QuestionQuery;
import ch.heigvd.amt.starkoverflow.domain.IRepository;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.tag.TagId;

import java.util.Collection;
import java.util.Optional;

public interface IQuestionRepository extends IRepository<Question,QuestionId> {
    public Collection<Question> find(QuestionQuery query);

    void addTag(QuestionId questionId, TagId tagId);

    Collection<Tag> getQuestionTags(QuestionId questionId);

    Collection<Answer> getQuestionAnswers(QuestionId questionId);

    Collection<Question> findByAuthor(String authorId);

    Collection<Question> findByTag(String tag);

    Optional<Answer> getAcceptedAnswer(QuestionId questionId);

    boolean hasAcceptedAnswer(QuestionId questionId);
}
