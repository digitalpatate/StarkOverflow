package ch.heigvd.amt.starkoverflow.ui.web.question;

import ch.heigvd.amt.starkoverflow.application.Tag.CreateTagCommand;
import ch.heigvd.amt.starkoverflow.application.Tag.TagService;
import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagsDTO;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.application.question.CreateQuestionCommand;
import ch.heigvd.amt.starkoverflow.application.question.QuestionService;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionDTO;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet(name="QuestionsQueryHandler", urlPatterns = "/questions")
public class QuestionsCommandHandler extends HttpServlet {

    @Inject @Named("QuestionService")
    private QuestionService questionService;

    @Inject @Named("TagService")
    private TagService tagService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Fetch tags
        TagsDTO tagsDTO = tagService.getTags();
        req.setAttribute("tags", tagsDTO);

        // Fetch questions
        QuestionsDTO questionsDTO = questionService.getQuestions();

        req.setAttribute("questions", questionsDTO);

        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("currentUser");
        ArrayList<Tag> tags = new ArrayList<>();

        for (String tagName : req.getParameterValues("tags")) {
            CreateTagCommand createTagCommand = CreateTagCommand.builder()
                    .name(tagName)
                    .build();

            tags.add(tagService.findOrCreateTag(createTagCommand));
        }

        CreateQuestionCommand createQuestionCommand = CreateQuestionCommand.builder()
                .content(req.getParameter("questionContent"))
                .title(req.getParameter("questionTitle"))
                .userId(userDTO.getId())
                .tags(tags)
                .build();

        questionService.createQuestion(createQuestionCommand);
        res.sendRedirect("/");
    }
}
