package databaseInteractor;

import Enums.Entities;
import entities.*;

import java.util.List;

public interface DatabaseInteractor {
    public List<Company> getCompanies();
    public List<Customer> getCustomers();
    public List<Developer> getDevelopers();
    public List<Project> getProjects();
    public List<Skill> getSkills();
    public Company getCompanyById(int id);
    public Customer getCustomerById(int id);
    public Developer getDeveloperById(int id);
    public Project getProjectById(int id);
    public Skill getSkillById(int id);
    public boolean deleteById(Entities entity, int id);
    public boolean createCompany(Company company);
    public boolean createCustomer(Customer customer);
    public boolean createDeveloper(Developer developer);
    public boolean createProject(Project project);
    public boolean createSkill(Skill skill);
    public boolean updateCompany(Company company);
    public boolean updateCustomer(Customer customer);
    public boolean updateDeveloper(Developer developer);
    public boolean updateProject(Project project);
    public boolean updateSkill(Skill skill);
    public int getSalarySum(int proj_id);
    public List<Developer> getDevelopersByProject(int proj_id);
//    public int getProjectSalariesSum();
//    public List<Integer> getProjectDevs();
//    public List<Integer> getJavaDevs();
//    public List<Integer> getMiddleDevs();
//    public List<Integer> getProjectDetails();
}
