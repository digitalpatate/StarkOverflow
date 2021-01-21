package ch.heigvd.amt.starkoverflow.application.Event;

public enum EventTypes {
    CREATE_A_QUESTION("create a question"),
    ANSWER_A_TAGGED_QUESTION("answer a tagged question"),
    COMMENT_A_QUESTION("comment a question"),
    VALID_ANSWER("get approval for one of your answer"),
    VOTE_FOR_ANSWER("vote for an answer"),
    FIND_THE_KEY("but where is the key ?");

    private String name;

    EventTypes(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
