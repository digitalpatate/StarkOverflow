package ch.heigvd.amt.starkoverflow.application;

import ch.heigvd.amt.starkoverflow.application.question.QuestionService;
import ch.heigvd.amt.starkoverflow.application.statistic.StatisticService;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.statistic.IStatisticRepository;
import ch.heigvd.amt.starkoverflow.domain.statistic.Statistic;
import ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory.InMemoryQuestionRepository;
import ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory.InMemoryStatisticRepository;

public class ServiceRegistry {

    private static ServiceRegistry instance;

    private static IQuestionRepository questionRepository;
    private static IStatisticRepository statisticRepository;
    private static QuestionService questionService;
    private static StatisticService statisticService;

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
        statisticRepository = new InMemoryStatisticRepository();
        statisticService = new StatisticService(statisticRepository);
    }

    public QuestionService getQuestionService() {
        return questionService;
    }

    public StatisticService getStatisticService() {
        return statisticService;
    }

}
