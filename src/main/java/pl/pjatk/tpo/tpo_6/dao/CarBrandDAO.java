package pl.pjatk.tpo.tpo_6.dao;

import pl.pjatk.tpo.tpo_6.model.CarBrand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CarBrandDAO {
    private DataSource dataSource;

    public CarBrandDAO() throws Exception {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        dataSource = (DataSource) envContext.lookup("jdbc/tpo_db");
    }

    public List<CarBrand> getCarBrands() throws Exception {
        List<CarBrand> carBrands = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            String sql = "SELECT id, name FROM brands";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                CarBrand carBrand = new CarBrand();
                carBrand.setId(rs.getInt("id"));
                carBrand.setName(rs.getString("name"));
                carBrands.add(carBrand);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return carBrands;
    }
}
