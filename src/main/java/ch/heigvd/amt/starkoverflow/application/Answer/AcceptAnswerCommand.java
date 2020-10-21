package ch.heigvd.amt.starkoverflow.application.Answer;

import lombok.Builder;
import lombok.Value;

@Builder(toBuilder = true)
@Value
public class AcceptAnswerCommand {
    String userId;
    String answerId;
}
