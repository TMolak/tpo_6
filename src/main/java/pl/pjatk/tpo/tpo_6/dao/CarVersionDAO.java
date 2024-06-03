package pl.pjatk.tpo.tpo_6.dao;

import pl.pjatk.tpo.tpo_6.model.CarVersion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CarVersionDAO {
    private DataSource dataSource;

    public CarVersionDAO() throws Exception {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        dataSource = (DataSource) envContext.lookup("jdbc/tpo_db");
    }
    public List<CarVersion> getCarVersionsByModel(int modelId) throws Exception {
        List<CarVersion> carVersions = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            String sql = "SELECT id, model_id, engine, horsepower, transmission FROM versions WHERE model_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, modelId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                CarVersion carVersion = new CarVersion();
                carVersion.setId(rs.getInt("id"));
                carVersion.setModelId(rs.getInt("model_id"));
                carVersion.setEngine(rs.getString("engine"));
                carVersion.setHorsepower(rs.getInt("horsepower"));
                carVersion.setTransmission(rs.getString("transmission"));
                carVersions.add(carVersion);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return carVersions;
    }


    public List<CarVersion> searchCarVersions(String brand, String model, Integer minHorsepower, Double minEngineSize, String transmission) throws Exception {
        List<CarVersion> carVersions = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            StringBuilder sql = new StringBuilder("SELECT v.id, v.model_id, v.engine, v.horsepower, v.transmission, b.name as brand_name, m.name as model_name ");
            sql.append("FROM versions v ");
            sql.append("JOIN models m ON v.model_id = m.id ");
            sql.append("JOIN brands b ON m.brand_id = b.id ");
            sql.append("WHERE 1=1 ");

            if (brand != null && !brand.isEmpty()) {
                sql.append("AND b.name = ? ");
            }
            if (model != null && !model.isEmpty()) {
                sql.append("AND m.name = ? ");
            }
            if (minHorsepower != null) {
                sql.append("AND v.horsepower >= ? ");
            }
            if (minEngineSize != null) {
                sql.append("AND v.engine >= ? ");
            }
            if (transmission != null && !transmission.isEmpty()) {
                sql.append("AND v.transmission = ? ");
            }

            stmt = conn.prepareStatement(sql.toString());
            int paramIndex = 1;

            if (brand != null && !brand.isEmpty()) {
                stmt.setString(paramIndex++, brand);
            }
            if (model != null && !model.isEmpty()) {
                stmt.setString(paramIndex++, model);
            }
            if (minHorsepower != null) {
                stmt.setInt(paramIndex++, minHorsepower);
            }
            if (minEngineSize != null) {
                stmt.setDouble(paramIndex++, minEngineSize);
            }
            if (transmission != null && !transmission.isEmpty()) {
                stmt.setString(paramIndex++, transmission);
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                CarVersion carVersion = new CarVersion();
                carVersion.setId(rs.getInt("id"));
                carVersion.setModelId(rs.getInt("model_id"));
                carVersion.setEngine(rs.getString("engine"));
                carVersion.setHorsepower(rs.getInt("horsepower"));
                carVersion.setTransmission(rs.getString("transmission"));
                carVersion.setBrandName(rs.getString("brand_name"));
                carVersion.setModelName(rs.getString("model_name"));
                carVersions.add(carVersion);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return carVersions;
    }
}
