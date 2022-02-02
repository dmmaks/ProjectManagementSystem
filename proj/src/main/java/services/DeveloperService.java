package services;

import models.Developer;
import models.DevsPerProject;
import repositories.DeveloperRepository;
import java.util.List;

public class DeveloperService implements BasicService<Developer> {
    private DeveloperRepository repository;

    public DeveloperService(DeveloperRepository repository) {
        this.repository = repository;
    }

    public List<Developer> getAll() {
        return repository.getAll();
    }

    public Developer getById(int id) {
        return repository.getById(id);
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }

    public boolean create(Developer item) {
        return repository.create(item);
    }

    public boolean update(Developer item) {
        return repository.update(item);
    }

    public int getSalarySum(int projId) {
        return repository.getSalarySum(projId);
    }

    public List<Developer> getDevelopersByProject(int projId) {
        return repository.getDevelopersByProject(projId);
    }

    public List<Developer> getJavaDevelopers() {
        return repository.getJavaDevelopers();
    }

    public List<Developer> getMiddleDevelopers() {
        return repository.getMiddleDevelopers();
    }

    public List<DevsPerProject> getDevelopersPerProject() {
        return repository.getDevelopersPerProject();
    }
}