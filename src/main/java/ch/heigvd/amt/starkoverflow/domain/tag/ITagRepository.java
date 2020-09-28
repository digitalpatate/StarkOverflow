package ch.heigvd.amt.starkoverflow.domain.tag;

import ch.heigvd.amt.starkoverflow.application.Tag.TagQuery;
import ch.heigvd.amt.starkoverflow.domain.IRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.vote.Vote;
import ch.heigvd.amt.starkoverflow.domain.vote.VoteId;

import java.util.Collection;

public interface ITagRepository extends IRepository<Tag, TagId> {
    Collection<Tag> find(TagQuery query);
}
