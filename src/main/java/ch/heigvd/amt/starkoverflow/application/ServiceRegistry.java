package ch.heigvd.amt.starkoverflow.application;

import ch.heigvd.amt.starkoverflow.application.question.QuestionService;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory.InMemoryQuestionRepository;

public class ServiceRegistry {

    private static ServiceRegistry instance;

    private static IQuestionRepository questionRepository;
    private static QuestionService questionService;

    public static ServiceRegistry getServiceRegistry(){
        if(instance == null){
            instance = new ServiceRegistry();
        }
        return instance;
    }

    private ServiceRegistry(){
        instance = this;
        questionRepository = new InMemoryQuestionRepository();
        questionService = new QuestionService(questionRepository);
    }

    public QuestionService getQuestionService() {
        return questionService;
    }
}
