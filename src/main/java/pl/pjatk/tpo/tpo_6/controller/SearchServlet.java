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

            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<title>Search Cars</title>");
            out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'>");
            out.println("<script src='popup.js'></script>");
            out.println("</head><body>");
            out.println("<div class='container mt-5'>");
            out.println("<h1 class='text-center'>Advanced Car Search</h1>");
            out.println("<form action='search-servlet' method='get' class='mt-4'>");
            out.println("<div class='form-row'>");
            out.println("<div class='form-group col-md-6'>");
            out.println("<label for='brand'>Brand:</label>");
            out.println("<input type='text' class='form-control' id='brand' name='brand'>");
            out.println("</div>");
            out.println("<div class='form-group col-md-6'>");
            out.println("<label for='model'>Model:</label>");
            out.println("<input type='text' class='form-control' id='model' name='model'>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='form-row'>");
            out.println("<div class='form-group col-md-6'>");
            out.println("<label for='minHorsepower'>Min Horsepower:</label>");
            out.println("<input type='number' class='form-control' id='minHorsepower' name='minHorsepower'>");
            out.println("</div>");
            out.println("<div class='form-group col-md-6'>");
            out.println("<label for='minEngineSize'>Min Engine Size (L):</label>");
            out.println("<input type='number' step='0.1' class='form-control' id='minEngineSize' name='minEngineSize'>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='form-row'>");
            out.println("<div class='form-group col-md-6 ev-field'>");
            out.println("<label for='transmission'>Transmission:</label>");
            out.println("<select id='transmission' name='transmission' class='form-control'>");
            out.println("<option value=''>Any</option>");
            out.println("<option value='Manual'>Manual</option>");
            out.println("<option value='Automatic'>Automatic</option>");
            out.println("</select>");
            out.println("</div>");
            out.println("<div class='form-group col-md-6'>");
            out.println("<div class='form-check mt-4'>");
            out.println("<input type='checkbox' class='form-check-input' onclick='handleEvClick(event)'>");
            out.println("<label class='form-check-label'>EV</label>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<button type='submit' class='btn btn-primary btn-block'>Search</button>");
            out.println("</form>");

            // Displaying the search results
            out.println("<div id='search-results' class='mt-5'>");
            if (carVersions.isEmpty()) {
                out.println("<p class='text-center'>No results found.</p>");
            } else {
                out.println("<table class='table table-bordered'>");
                out.println("<thead class='thead-light'><tr><th>ID</th><th>Brand</th><th>Model</th><th>Engine</th><th>Horsepower</th><th>Transmission</th><th>Photo</th></tr></thead>");
                out.println("<tbody>");
                for (CarVersion carVersion : carVersions) {
                    out.println("<tr>");
                    out.println("<td>" + carVersion.getId() + "</td>");
                    out.println("<td>" + carVersion.getBrandName() + "</td>");
                    out.println("<td>" + carVersion.getModelName() + "</td>");
                    out.println("<td>" + carVersion.getEngine() + "</td>");
                    out.println("<td>" + carVersion.getHorsepower() + "</td>");
                    out.println("<td>" + carVersion.getTransmission() + "</td>");
                    out.println("<td><button class='btn btn-link' onclick=\"handlePhotoClick('" + carVersion.getBrandName() + "', '" + carVersion.getModelName() + "')\">View Photo</button></td>");
                    out.println("</tr>");
                }
                out.println("</tbody></table>");
            }
            out.println("</div>");
            out.println("</div>");
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
