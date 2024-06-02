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

@WebServlet(name = "versionServlet", value = "/version-servlet")
public class VersionServlet extends HttpServlet {

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
            int modelId = Integer.parseInt(request.getParameter("modelId"));
            List<CarVersion> carVersions = carVersionDAO.getCarVersionsByModel(modelId);
            out.println("<html><body>");
            out.println("<h1>Car Versions List</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Engine</th><th>Horsepower</th><th>Transmission</th></tr>");
            for (CarVersion carVersion : carVersions) {
                out.println("<tr>");
                out.println("<td>" + carVersion.getId() + "</td>");
                out.println("<td>" + carVersion.getEngine() + "</td>");
                out.println("<td>" + carVersion.getHorsepower() + "</td>");
                out.println("<td>" + carVersion.getTransmission() + "</td>");
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
