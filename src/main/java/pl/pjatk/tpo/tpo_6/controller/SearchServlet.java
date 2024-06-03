package pl.pjatk.tpo.tpo_6.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            String brand = request.getParameter("brand");
            String model = request.getParameter("model");
            String minHorsepowerParam = request.getParameter("minHorsepower");
            String minEngineSizeParam = request.getParameter("minEngineSize");
            String transmission = request.getParameter("transmission");

            Integer minHorsepower = minHorsepowerParam != null && !minHorsepowerParam.isEmpty() ? Integer.parseInt(minHorsepowerParam) : null;
            Double minEngineSize = minEngineSizeParam != null && !minEngineSizeParam.isEmpty() ? Double.parseDouble(minEngineSizeParam) : null;

            List<CarVersion> carVersions = carVersionDAO.searchCarVersions(brand, model, minHorsepower, minEngineSize, transmission);
            out.println("<html><head>");
            out.println("<script src='popup.js'></script>");
            out.println("</head><body>");
            out.println("<h1>Search Results</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Brand</th><th>Model</th><th>Engine</th><th>Horsepower</th><th>Transmission</th><th>Photo</th></tr>");
            for (CarVersion carVersion : carVersions) {
                out.println("<tr>");
                out.println("<td>" + carVersion.getId() + "</td>");
                out.println("<td>" + carVersion.getBrandName() + "</td>");
                out.println("<td>" + carVersion.getModelName() + "</td>");
                out.println("<td>" + carVersion.getEngine() + "</td>");
                out.println("<td>" + carVersion.getHorsepower() + "</td>");
                out.println("<td>" + carVersion.getTransmission() + "</td>");
                out.println("<td><button onclick=\"handlePhotoClick('" + carVersion.getBrandName() + "', '" + carVersion.getModelName() + "')\">View Photo</button></td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body></html>");
        } catch (Exception e) {
            e.printStackTrace(out);
        } finally {
            out.close();
        }
    }

    public void destroy() {
    }
}
