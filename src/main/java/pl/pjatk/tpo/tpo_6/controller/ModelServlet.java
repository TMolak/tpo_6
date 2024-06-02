package pl.pjatk.tpo.tpo_6.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import pl.pjatk.tpo.tpo_6.dao.CarModelDAO;
import pl.pjatk.tpo.tpo_6.model.CarModel;

@WebServlet(name = "modelsServlet", value = "/models-servlet")
public class ModelServlet extends HttpServlet {

    private CarModelDAO carModelDAO;

    public void init() throws ServletException {
        try {
            carModelDAO = new CarModelDAO();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            int brandId = Integer.parseInt(request.getParameter("brandId"));
            List<CarModel> carModels = carModelDAO.getCarModelsByBrand(brandId);
            out.println("<html><body>");
            out.println("<h1>Car Models List</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Action</th></tr>");
            for (CarModel carModel : carModels) {
                out.println("<tr>");
                out.println("<td>" + carModel.getId() + "</td>");
                out.println("<td>" + carModel.getName() + "</td>");
                out.println("<td><form action='version-servlet' method='get'>");
                out.println("<input type='hidden' name='modelId' value='" + carModel.getId() + "'/>");
                out.println("<input type='submit' value='View Versions'/>");
                out.println("</form></td>");
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
