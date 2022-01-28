package databaseInteractor;

import enums.Entities;
import entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreInteractor implements DatabaseInteractor {
    private Connection conn;

    public PostgreInteractor() throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://localhost/postgres?user=postgres&password=cvbnM_987_Mnbvc";
        this.conn = DriverManager.getConnection(url);
    }

    public List<Company> getCompanies() {
        List<Company> resList = new ArrayList<>();
        String query = "SELECT * FROM companies";
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

    public List<Customer> getCustomers() {
        List<Customer> resList = new ArrayList<>();
        String query = "SELECT * FROM customers";
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

    public List<Developer> getDevelopers() {
        List<Developer> resList = new ArrayList<>();
        String query = "SELECT * FROM developers";
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

    public List<Project> getProjects() {
        List<Project> resList = new ArrayList<>();
        String query = "SELECT * FROM projects";
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

    public List<Skill> getSkills() {
        List<Skill> resList = new ArrayList<>();
        String query = "SELECT * FROM skills";
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

    public Company getCompanyById(int id) {
        String query = "SELECT * FROM companies WHERE id = ?";
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

    public Customer getCustomerById(int id) {
        String query = "SELECT * FROM customers WHERE id = ?";
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

    public Developer getDeveloperById(int id) {
        String query = "SELECT * FROM developers WHERE id = ?";
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

    public Project getProjectById(int id) {
        String query = "SELECT * FROM projects WHERE id = ?";
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

    public Skill getSkillById(int id) {
        String query = "SELECT * FROM skills WHERE id = ?";
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

    public boolean deleteById(Entities entity, int id) {
        String entityStr = entity.toString().toLowerCase();
        boolean deleted = false;
        String query = "DELETE FROM " + entityStr + " WHERE id = ?";
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

    public boolean createCompany(Company company) {
        boolean inserted = false;
        String query = "INSERT INTO companies (name, foundation_date) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, company.getName());
            pstmt.setDate(2, Date.valueOf(company.getFoundationDate()));
            int rowCount = pstmt.executeUpdate();
            if (rowCount != 0) {
                inserted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inserted;
    }

    public boolean createCustomer(Customer customer) {
        boolean inserted = false;
        String query = "INSERT INTO customers (name, country) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getCountry());
            int rowCount = pstmt.executeUpdate();
            if (rowCount != 0) {
                inserted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inserted;
    }

    public boolean createDeveloper(Developer developer) {
        boolean inserted = false;
        String query = "INSERT INTO developers (name, birth_date, sex, salary) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, developer.getName());
            pstmt.setDate(2, Date.valueOf(developer.getBirthDate()));
            pstmt.setString(3, developer.getSex());
            pstmt.setInt(4, developer.getSalary());
            int rowCount = pstmt.executeUpdate();
            if (rowCount != 0) {
                inserted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inserted;
    }

    public boolean createProject(Project project) {
        boolean inserted = false;
        String query = "INSERT INTO projects (name, start_date, customer_id, company_id, cost) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, project.getName());
            pstmt.setDate(2, Date.valueOf(project.getStartDate()));
            pstmt.setInt(3, project.getCustomerId());
            pstmt.setInt(4, project.getCompanyId());
            pstmt.setInt(5, project.getCost());
            int rowCount = pstmt.executeUpdate();
            if (rowCount != 0) {
                inserted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inserted;
    }

    public boolean createSkill(Skill skill) {
        boolean inserted = false;
        String query = "INSERT INTO skills (area, level) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, skill.getArea());
            pstmt.setString(2, skill.getLevel());
            int rowCount = pstmt.executeUpdate();
            if (rowCount != 0) {
                inserted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inserted;
    }

    public boolean updateCompany(Company company) {
        boolean updated = false;
        String query = "UPDATE companies SET name = ?, foundation_date = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, company.getName());
            pstmt.setDate(2, Date.valueOf(company.getFoundationDate()));
            pstmt.setInt(3, company.getId());
            int rowCount = pstmt.executeUpdate();
            if (rowCount != 0) {
                updated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    public boolean updateCustomer(Customer customer) {
        boolean updated = false;
        String query = "UPDATE customers SET name = ?, country = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getCountry());
            pstmt.setInt(3, customer.getId());
            int rowCount = pstmt.executeUpdate();
            if (rowCount != 0) {
                updated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    public boolean updateDeveloper(Developer developer) {
        boolean updated = false;
        String query = "UPDATE developers SET name = ?, birth_date = ?, sex = ?, salary = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, developer.getName());
            pstmt.setDate(2, Date.valueOf(developer.getBirthDate()));
            pstmt.setString(3, developer.getSex());
            pstmt.setInt(4, developer.getSalary());
            pstmt.setInt(5, developer.getId());
            int rowCount = pstmt.executeUpdate();
            if (rowCount != 0) {
                updated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    public boolean updateProject(Project project) {
        boolean updated = false;
        String query = "UPDATE projects SET name = ?, start_date = ?, customer_id = ?, company_id = ?, cost = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, project.getName());
            pstmt.setDate(2, Date.valueOf(project.getStartDate()));
            pstmt.setInt(3, project.getCustomerId());
            pstmt.setInt(4, project.getCompanyId());
            pstmt.setInt(5, project.getCost());
            pstmt.setInt(6, project.getId());
            int rowCount = pstmt.executeUpdate();
            if (rowCount != 0) {
                updated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    public boolean updateSkill(Skill skill) {
        boolean updated = false;
        String query = "UPDATE skills SET area = ?, level = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, skill.getArea());
            pstmt.setString(2, skill.getLevel());
            pstmt.setInt(3, skill.getId());
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
        String query = "SELECT SUM(salary) FROM developers INNER JOIN developers_projects ON developers.id = developers_projects.dev_id " +
                "INNER JOIN projects ON projects.id = developers_projects.proj_id WHERE proj_id = ?";
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
        String query = "SELECT id, name, birth_date, sex, salary FROM developers INNER JOIN developers_projects ON developers.id = developers_projects.dev_id WHERE proj_id = ?";
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
        String query = "SELECT developers.id, name, birth_date, sex, salary FROM developers INNER JOIN developers_skills ON developers.id = developers_skills.dev_id INNER JOIN skills ON skills.id = developers_skills.skill_id WHERE skills.area ILIKE 'Java'";
        return this.getDevelopersByQuery(query);
    }

    public List<Developer> getMiddleDevelopers() {
        String query = "SELECT distinct developers.id, name, birth_date, sex, salary FROM developers INNER JOIN developers_skills ON developers.id = developers_skills.dev_id INNER JOIN skills ON skills.id = developers_skills.skill_id WHERE skills.level ILIKE 'middle'";
        return this.getDevelopersByQuery(query);
    }

    public List<DevsPerProject> getDevelopersPerProject() {
        List<DevsPerProject> resList = new ArrayList<>();
        String query = "SELECT projects.name, projects.start_date, COUNT(dev_id) FROM developers INNER JOIN developers_projects ON developers.id = developers_projects.dev_id INNER JOIN projects ON projects.id = developers_projects.proj_id GROUP BY projects.name, projects.start_date";
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
