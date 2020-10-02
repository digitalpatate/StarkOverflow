package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.application.Tag.TagQuery;
import ch.heigvd.amt.starkoverflow.domain.tag.ITagRepository;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.tag.TagId;

import java.util.Collection;

public class InMemoryTagRepository extends InMemoryRepository<Tag,TagId> implements ITagRepository {

    @Override
    public Collection<Tag> find(TagQuery query) {
        return null;
    }
}
