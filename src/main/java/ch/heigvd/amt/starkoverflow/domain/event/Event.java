package ch.heigvd.amt.starkoverflow.domain.event;

import ch.heigvd.amt.starkoverflow.application.Event.EventTypes;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.UserId;
import ch.heigvd.amt.starkoverflow.domain.comment.ICommentable;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.tag.TagId;
import ch.heigvd.amt.starkoverflow.domain.vote.IVotable;
import jdk.jfr.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class Event {
    private UserId userId;
    private OffsetDateTime timestamp;
    private String type;

    public Event(UserId userId, String type) {
        this.userId = userId;
        this.timestamp = OffsetDateTime.now();
        this.type = type;
    }

}
