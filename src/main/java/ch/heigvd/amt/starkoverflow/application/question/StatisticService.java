package ch.heigvd.amt.starkoverflow.application.question;

public class StatisticService {
    /*    private IQuestionRepository questionRepository;

    public QuestionService(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void createQuestion(CreateQuestionCommand command){
        Question question = command.createEntity();
        questionRepository.save(question);
    }

    public QuestionsDTO getQuestion(QuestionQuery query){
        Collection<Question> questions = questionRepository.find(query);

        List<QuestionDTO> questionsDTO = questions.stream().map(question -> QuestionDTO.builder().build()).collect(Collectors.toList());

        return QuestionsDTO.builder().questions(questionsDTO).build();
    }*/

    private IStatisticRepository statisticRepository;

    public StatisticService(IStatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public StatisticDTO getStatistic(StatisticQuery query) {

    }
}
