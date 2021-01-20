package ch.heigvd.amt.starkoverflow.domain.event;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.UserId;
import ch.heigvd.amt.starkoverflow.domain.comment.ICommentable;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.tag.TagId;
import ch.heigvd.amt.starkoverflow.domain.vote.IVotable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class Event implements IEntity {
    private EventId id;
    private UserId userId;
    private OffsetDateTime timestamp;
    private String type;

    public Event(UserId userId, OffsetDateTime timestamp, String type) {
        this.id = new EventId();
        this.userId = userId;
        this.timestamp = timestamp;
        this.type = type;
    }

}
