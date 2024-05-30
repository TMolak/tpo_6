package pl.pjatk.tpo.tpo_6.DAO;


import pl.pjatk.tpo.tpo_6.model.CarModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CarModelDAO {
    private DataSource dataSource;

    public CarModelDAO() throws Exception {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        dataSource = (DataSource) envContext.lookup("jdbc/tpo_db");
    }

    public List<CarModel> getCarModelsByBrand(int brandId) throws Exception {
        List<CarModel> carModels = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            String sql = "SELECT id, name FROM models WHERE brand_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, brandId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                CarModel carModel = new CarModel();
                carModel.setId(rs.getInt("id"));
                carModel.setName(rs.getString("name"));
                carModels.add(carModel);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return carModels;
    }
}
