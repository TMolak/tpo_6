package pl.pjatk.tpo.tpo_6.controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.RequestDispatcher;
import pl.pjatk.tpo.tpo_6.dao.CarVersionDAO;
import pl.pjatk.tpo.tpo_6.model.CarVersion;

@WebServlet(name = "searchServlet", value = "/search-servlet")
public class SearchServlet extends HttpServlet {

    private CarVersionDAO carVersionDAO;

    public void init() throws ServletException {
        try {
            carVersionDAO = new CarVersionDAO();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String brand = request.getParameter("brand");
            String model = request.getParameter("model");
            String minHorsepowerParam = request.getParameter("minHorsepower");
            String minEngineSizeParam = request.getParameter("minEngineSize");
            String transmission = request.getParameter("transmission");

            Integer minHorsepower = minHorsepowerParam != null && !minHorsepowerParam.isEmpty() ? Integer.parseInt(minHorsepowerParam) : null;
            Double minEngineSize = minEngineSizeParam != null && !minEngineSizeParam.isEmpty() ? Double.parseDouble(minEngineSizeParam) : null;

            List<CarVersion> carVersions = carVersionDAO.searchCarVersions(brand, model, minHorsepower, minEngineSize, transmission);

            request.setAttribute("carVersions", carVersions);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/searchResults.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public void destroy() {
    }
}
