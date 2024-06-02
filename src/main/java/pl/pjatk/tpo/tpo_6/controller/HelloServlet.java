package pl.pjatk.tpo.tpo_6.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import pl.pjatk.tpo.tpo_6.dao.CarBrandDAO;
import pl.pjatk.tpo.tpo_6.model.CarBrand;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private CarBrandDAO carBrandDAO;

    public void init() throws ServletException {
        try {
            carBrandDAO = new CarBrandDAO();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            List<CarBrand> carBrands = carBrandDAO.getCarBrands();
            out.println("<html><body>");
            out.println("<h1>Car Brands List</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Action</th></tr>");
            for (CarBrand carBrand : carBrands) {
                out.println("<tr>");
                out.println("<td>" + carBrand.getId() + "</td>");
                out.println("<td>" + carBrand.getName() + "</td>");
                out.println("<td><form action='models-servlet' method='get'>");
                out.println("<input type='hidden' name='brandId' value='" + carBrand.getId() + "'/>");
                out.println("<input type='submit' value='View Models'/>");
                out.println("</form></td>");
                out.println("</tr>");
            }
            out.println("</table>");
            // Add a link to navigate to the search form
            out.println("<br><a href='search-form.html'>Go to Search Form</a>");
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
