package ch.heigvd.amt.starkoverflow.ui.web.question;

import ch.heigvd.amt.starkoverflow.application.Tag.CreateTagCommand;
import ch.heigvd.amt.starkoverflow.application.Tag.TagService;
import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagsDTO;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.application.question.CreateQuestionCommand;
import ch.heigvd.amt.starkoverflow.application.question.QuestionService;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="QuestionsCommandHandler", urlPatterns = "/questions")
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
        if (!req.getParameterMap().containsKey("tag")) {
            QuestionsDTO questionsDTO = questionService.getQuestions();

            req.setAttribute("questions", questionsDTO);
        } else {
            String tag = req.getParameter("tag");
            QuestionsDTO questionsDTO = questionService.getQuestionsByTag(tag);

            req.setAttribute("questions", questionsDTO);
            req.setAttribute("tag", tag);
        }

        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("currentUser");
        ArrayList<Tag> tags = new ArrayList<>();

        final String[] reqTags = req.getParameterValues("tags");

        if(reqTags != null) {
            for (String tagName : reqTags) {
                CreateTagCommand createTagCommand = CreateTagCommand.builder()
                        .name(tagName)
                        .build();

                tags.add(tagService.findOrCreateTag(createTagCommand));
            }
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
