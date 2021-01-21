package ch.heigvd.amt.starkoverflow.ui.web.answer;

import ch.heigvd.amt.starkoverflow.application.Answer.AnswerService;
import ch.heigvd.amt.starkoverflow.application.Answer.CreateAnswerCommand;
import ch.heigvd.amt.starkoverflow.application.Event.EventService;
import ch.heigvd.amt.starkoverflow.application.Event.EventTypes;
import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagDTO;
import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagsDTO;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.application.question.QuestionService;
import ch.heigvd.amt.starkoverflow.domain.UserId;
import ch.heigvd.amt.starkoverflow.domain.event.Event;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="AnswerCommandHandler", urlPatterns = "/answer")
public class AnswerCommandHandler extends HttpServlet {

    @Inject @Named("AnswerService")
    AnswerService answerService;

    @Inject @Named("QuestionService")
    QuestionService questionService;

    @Inject @Named("EventService")
    EventService eventService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("currentUser");

        CreateAnswerCommand command = CreateAnswerCommand.builder()
                .userId(userDTO.getId())
                .questionId(req.getParameter("questionId"))
                .content(req.getParameter("answerContent"))
                .build();

        answerService.createAnswer(command);

        TagsDTO tagsDTO = questionService.getQuestionTags(new QuestionId(req.getParameter("questionId")));

        // Trigger tags events
        for(TagDTO tagDTO : tagsDTO.getTags()) {
            String conditionType = EventTypes.ANSWER_A_TAGGED_QUESTION + "_" + tagDTO.getName();

            Event event = new Event(
                    new UserId(userDTO.getId()), conditionType
            );

            eventService.triggerEvent(event);
        }

        resp.sendRedirect("/question/" + req.getParameter("questionId"));
    }
}
