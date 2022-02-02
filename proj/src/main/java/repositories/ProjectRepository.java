package repositories;

import models.Project;
import properties.AppProperties;
import repositories.DBInteractor.PostgreInteractor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository implements BasicRepository<Project> {
    private static ProjectRepository instance;
    private Connection conn;

    private ProjectRepository() throws SQLException {
        conn = PostgreInteractor.getInstance().getConnection();
    }

    public static ProjectRepository getInstance() {
        if (instance == null) {
            try {
                instance = new ProjectRepository();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return instance;
    }

    public List<Project> getAll() {
        List<Project> resList = new ArrayList<>();
        String query = AppProperties.getProperty("sql.projects.getAll");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Project project = new Project(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("start_date"), resultSet.getInt("customer_id"), resultSet.getInt("company_id"), resultSet.getInt("cost"));
                resList.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }

    public Project getById(int id) {
        String query = AppProperties.getProperty("sql.projects.getById");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                Project project = new Project(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("start_date"), resultSet.getInt("customer_id"), resultSet.getInt("company_id"), resultSet.getInt("cost"));

                return project;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(int id) {
        boolean deleted = false;
        String query = AppProperties.getProperty("sql.projects.delete");
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

    public boolean create(Project item) {
        boolean inserted = false;
        String query = AppProperties.getProperty("sql.projects.create");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, item.getName());
            pstmt.setDate(2, Date.valueOf(item.getStartDate()));
            pstmt.setInt(3, item.getCustomerId());
            pstmt.setInt(4, item.getCompanyId());
            pstmt.setInt(5, item.getCost());
            int rowCount = pstmt.executeUpdate();
            if (rowCount != 0) {
                inserted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inserted;
    }

    public boolean update(Project item) {
        boolean updated = false;
        String query = AppProperties.getProperty("sql.projects.update");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, item.getName());
            pstmt.setDate(2, Date.valueOf(item.getStartDate()));
            pstmt.setInt(3, item.getCustomerId());
            pstmt.setInt(4, item.getCompanyId());
            pstmt.setInt(5, item.getCost());
            pstmt.setInt(6, item.getId());
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