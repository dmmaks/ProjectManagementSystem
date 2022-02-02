package repositories;

import models.Skill;
import properties.AppProperties;
import repositories.DBInteractor.PostgreInteractor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillRepository implements BasicRepository<Skill> {
    private static SkillRepository instance;
    private Connection conn;

    private SkillRepository() throws SQLException {
        conn = PostgreInteractor.getInstance().getConnection();
    }

    public static SkillRepository getInstance() {
        if (instance == null) {
            try {
                instance = new SkillRepository();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return instance;
    }

    public List<Skill> getAll() {
        List<Skill> resList = new ArrayList<>();
        String query = AppProperties.getProperty("sql.skills.getAll");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Skill skill = new Skill(resultSet.getInt("id"), resultSet.getString("area"), resultSet.getString("level"));
                resList.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }

    public Skill getById(int id) {
        String query = AppProperties.getProperty("sql.skills.getById");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                Skill skill = new Skill(resultSet.getInt("id"), resultSet.getString("area"), resultSet.getString("level"));
                return skill;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(int id) {
        boolean deleted = false;
        String query = AppProperties.getProperty("sql.skills.delete");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int rowCount = pstmt.executeUpdate();
            if (rowCount != 0) {
                deleted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    public boolean create(Skill item) {
        boolean inserted = false;
        String query = AppProperties.getProperty("sql.skills.create");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, item.getArea());
            pstmt.setString(2, item.getLevel());
            int rowCount = pstmt.executeUpdate();
            if (rowCount != 0) {
                inserted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inserted;
    }

    public boolean update(Skill item) {
        boolean updated = false;
        String query = AppProperties.getProperty("sql.skills.update");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, item.getArea());
            pstmt.setString(2, item.getLevel());
            pstmt.setInt(3, item.getId());
            int rowCount = pstmt.executeUpdate();
            if (rowCount != 0) {
                updated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }
}