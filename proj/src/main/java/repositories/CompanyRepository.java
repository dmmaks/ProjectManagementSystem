package repositories;

import models.Company;
import properties.AppProperties;
import repositories.DBInteractor.PostgreInteractor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository implements BasicRepository<Company> {
    private static CompanyRepository instance;
    private Connection conn;

    private CompanyRepository() throws SQLException {
        conn = PostgreInteractor.getInstance().getConnection();
    }

    public static CompanyRepository getInstance() {
        if (instance == null) {
            try {
                instance = new CompanyRepository();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return instance;
    }

    public List<Company> getAll() {
        List<Company> resList = new ArrayList<>();
        String query = AppProperties.getProperty("sql.companies.getAll");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Company company = new Company(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("foundation_date"));
                resList.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }

    public Company getById(int id) {
        String query = AppProperties.getProperty("sql.companies.getById");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                Company company = new Company(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("foundation_date"));
                return company;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(int id) {
        boolean deleted = false;
        String query = AppProperties.getProperty("sql.companies.delete");
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

    public boolean create(Company item) {
        boolean inserted = false;
        String query = AppProperties.getProperty("sql.companies.create");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, item.getName());
            pstmt.setDate(2, Date.valueOf(item.getFoundationDate()));
            int rowCount = pstmt.executeUpdate();
            if (rowCount != 0) {
                inserted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inserted;
    }

    public boolean update(Company item) {
        boolean updated = false;
        String query = AppProperties.getProperty("sql.companies.update");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, item.getName());
            pstmt.setDate(2, Date.valueOf(item.getFoundationDate()));
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
