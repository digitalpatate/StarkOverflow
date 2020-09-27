package ch.heigvd.amt.starkoverflow.application;

import ch.heigvd.amt.starkoverflow.application.question.QuestionFacade;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory.InMemoryQuestionRepository;
import lombok.Getter;

public class ServiceRegistry {

    private static ServiceRegistry instance;

    private static IQuestionRepository questionRepository;
    private static QuestionFacade questionFacade;

    public static ServiceRegistry getServiceRegistry(){
        if(instance == null){
            instance = new ServiceRegistry();
        }
        return instance;
    }

    private ServiceRegistry(){
        instance = this;
        questionRepository = new InMemoryQuestionRepository();
        questionFacade = new QuestionFacade(questionRepository);
    }

    public QuestionFacade questionFacade() {
        return questionFacade;
    }
}
