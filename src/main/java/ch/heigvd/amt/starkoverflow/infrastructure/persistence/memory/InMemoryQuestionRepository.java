package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.application.question.QuestionQuery;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryQuestionRepository implements IQuestionRepository {

    private Map<QuestionId,Question> store = new ConcurrentHashMap<>();

    @Override
    public void save(Question question) {
        store.put(question.getId(),question);
    }

    @Override
    public void remove(QuestionId questionId) {
        store.remove(questionId);
    }

    @Override
    public Optional<Question> findById(QuestionId questionId) {
        Question question = store.get(questionId);
        if(question == null){
            return Optional.empty();
        }
        return Optional.of(question);
    }

    @Override
    public Collection<Question> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Collection<Question> find(QuestionQuery query) {
        return findAll();
    }
}
