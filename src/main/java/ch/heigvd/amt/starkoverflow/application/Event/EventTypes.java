package ch.heigvd.amt.starkoverflow.application.Event;

public enum EventTypes {
    CREATE_A_QUESTION("createaquestion"),
    ANSWER_A_TAGGED_QUESTION("answerataggedquestion"),
    COMMENT_A_QUESTION("commentaquestion"),
    VALID_ANSWER("validanswer"),
    VOTE_FOR_ANSWER("cookies"),
    FIND_THE_KEY("findthekey");

    private String name;

    EventTypes(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
