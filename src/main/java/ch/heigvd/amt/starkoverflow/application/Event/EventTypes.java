package ch.heigvd.amt.starkoverflow.application.Event;

public enum EventTypes {
    QUESTION_CREATION("questionCreation");

    private final String name;

    EventTypes(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
