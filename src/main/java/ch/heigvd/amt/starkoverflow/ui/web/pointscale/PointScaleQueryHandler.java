package ch.heigvd.amt.starkoverflow.ui.web.pointscale;

import ch.heigvd.amt.starkoverflow.application.PointScale.PointScaleService;
import ch.heigvd.amt.starkoverflow.application.PointScale.dto.PointScaleDTO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="PointScaleQueryHandler", urlPatterns = "/pointScales")
public class PointScaleQueryHandler extends HttpServlet {

    @Inject
    @Named("PointScaleService")
    private PointScaleService pointScaleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<PointScaleDTO> pointScaleDTOS = pointScaleService.getAllPointScale();
        System.out.println(pointScaleDTOS);
        req.setAttribute("pointScales", pointScaleDTOS);
        req.getRequestDispatcher("/WEB-INF/views/pointScales.jsp").forward(req,res);

    }
}
