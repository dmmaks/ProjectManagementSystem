package services;

import models.Project;
import repositories.ProjectRepository;

import java.util.List;

public class ProjectService implements BasicService<Project> {
    private ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public List<Project> getAll() {
        return repository.getAll();
    }

    public Project getById(int id) {
        return repository.getById(id);
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }

    public boolean create(Project item) {
        return repository.create(item);
    }

    public boolean update(Project item) {
        return repository.update(item);
    }

}