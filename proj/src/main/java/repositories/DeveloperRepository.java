package repositories;

import models.Developer;
import models.DevsPerProject;
import properties.AppProperties;
import repositories.DBInteractor.PostgreInteractor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperRepository implements BasicRepository<Developer> {
    private static DeveloperRepository instance;
    private Connection conn;

    private DeveloperRepository() throws SQLException {
        conn = PostgreInteractor.getInstance().getConnection();
    }

    public static DeveloperRepository getInstance() {
        if (instance == null) {
            try {
                instance = new DeveloperRepository();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return instance;
    }

    public List<Developer> getAll() {
        List<Developer> resList = new ArrayList<>();
        String query = AppProperties.getProperty("sql.developers.getAll");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Developer developer = new Developer(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("birth_date"), resultSet.getString("sex"), resultSet.getInt("salary"));
                resList.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }

    public Developer getById(int id) {
        String query = AppProperties.getProperty("sql.developers.getById");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {

                Developer developer = new Developer(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("birth_date"), resultSet.getString("sex"), resultSet.getInt("salary"));
                return developer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(int id) {
        boolean deleted = false;
        String query = AppProperties.getProperty("sql.developers.delete");
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

    public boolean create(Developer item) {
        boolean inserted = false;
        String query = AppProperties.getProperty("sql.developers.create");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, item.getName());
            pstmt.setDate(2, Date.valueOf(item.getBirthDate()));
            pstmt.setString(3, item.getSex());
            pstmt.setInt(4, item.getSalary());
            int rowCount = pstmt.executeUpdate();
            if (rowCount != 0) {
                inserted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inserted;
    }

    public boolean update(Developer item) {
        boolean updated = false;
        String query = AppProperties.getProperty("sql.developers.update");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, item.getName());
            pstmt.setDate(2, Date.valueOf(item.getBirthDate()));
            pstmt.setString(3, item.getSex());
            pstmt.setInt(4, item.getSalary());
            pstmt.setInt(5, item.getId());
            int rowCount = pstmt.executeUpdate();
            if (rowCount != 0) {
                updated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    public int getSalarySum(int projId) {
        String query = AppProperties.getProperty("sql.developers.getSalarySum");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, projId);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                int sum = resultSet.getInt("sum");
                return sum;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Developer> getDevelopersByProject(int projId) {
        List<Developer> resList = new ArrayList<>();
        String query = AppProperties.getProperty("sql.developers.getByProject");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, projId);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Developer developer = new Developer(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("birth_date"), resultSet.getString("sex"), resultSet.getInt("salary"));
                resList.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }

    private List<Developer> getDevelopersByQuery(String query) {
        List<Developer> resList = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Developer developer = new Developer(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("birth_date"), resultSet.getString("sex"), resultSet.getInt("salary"));
                resList.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }

    public List<Developer> getJavaDevelopers() {
        String query = AppProperties.getProperty("sql.developers.getJavaDevelopers");
        return this.getDevelopersByQuery(query);
    }

    public List<Developer> getMiddleDevelopers() {
        String query = AppProperties.getProperty("sql.developers.getMiddleDevelopers");
        return this.getDevelopersByQuery(query);
    }

    public List<DevsPerProject> getDevelopersPerProject() {
        List<DevsPerProject> resList = new ArrayList<>();
        String query = AppProperties.getProperty("sql.developers.getDevelopersPerProject");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                DevsPerProject devsCount = new DevsPerProject(resultSet.getString("start_date"), resultSet.getString("name"), resultSet.getInt("count"));
                resList.add(devsCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }
}