package repositories;

import models.Customer;
import properties.AppProperties;
import repositories.DBInteractor.PostgreInteractor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements BasicRepository<Customer> {
    private static CustomerRepository instance;
    private Connection conn;

    private CustomerRepository() throws SQLException {
        conn = PostgreInteractor.getInstance().getConnection();
    }

    public static CustomerRepository getInstance() {
        if (instance == null) {
            try {
                instance = new CustomerRepository();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return instance;
    }

    public List<Customer> getAll() {
        List<Customer> resList = new ArrayList<>();
        String query = AppProperties.getProperty("sql.customers.getAll");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("country"));
                resList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }

    public Customer getById(int id) {
        String query = AppProperties.getProperty("sql.customers.getById");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("country"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(int id) {
        boolean deleted = false;
        String query = AppProperties.getProperty("sql.customers.delete");
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

    public boolean create(Customer item) {
        boolean inserted = false;
        String query = AppProperties.getProperty("sql.customers.create");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getCountry());
            int rowCount = pstmt.executeUpdate();
            if (rowCount != 0) {
                inserted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inserted;
    }

    public boolean update(Customer item) {
        boolean updated = false;
        String query = AppProperties.getProperty("sql.customers.update");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getCountry());
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