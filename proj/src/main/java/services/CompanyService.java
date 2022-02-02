package services;

import models.Company;
import repositories.CompanyRepository;
import java.util.List;

public class CompanyService implements BasicService<Company> {
    private CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<Company> getAll() {
        return repository.getAll();
    }

    public Company getById(int id) {
        return repository.getById(id);
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }

    public boolean create(Company item) {
        return repository.create(item);
    }

    public boolean update(Company item) {
        return repository.update(item);
    }
}
