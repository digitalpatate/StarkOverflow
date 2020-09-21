package ch.heigvd.amt.starkoverflow.model;

import lombok.Builder;
import lombok.Value;

@Value @Builder
public class Question {
    String title;
    String content;
}
