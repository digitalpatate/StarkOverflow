package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.application.Tag.TagQuery;
import ch.heigvd.amt.starkoverflow.application.question.QuestionQuery;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.tag.ITagRepository;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.tag.TagId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTagRepository implements ITagRepository {

    private Map<TagId, Tag> store = new ConcurrentHashMap<>();

    @Override
    public void save(Tag tag) {
        store.put(tag.getId(),tag);
    }

    @Override
    public void remove(TagId id) {
        store.remove(id);
    }

    @Override
    public Optional<Tag> findById(TagId id) {
        Tag tag = store.get(id);
        if(tag == null){
            return Optional.empty();
        }
        return Optional.of(tag);
    }

    @Override
    public Collection<Tag> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Collection<Tag> find(TagQuery query) {
        return findAll();
    }
}
