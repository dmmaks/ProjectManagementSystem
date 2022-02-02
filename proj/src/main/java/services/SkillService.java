package services;

import models.Skill;
import repositories.SkillRepository;

import java.util.List;

public class SkillService implements BasicService<Skill> {
    private SkillRepository repository;

    public SkillService(SkillRepository repository) {
        this.repository = repository;
    }

    public List<Skill> getAll() {
        return repository.getAll();
    }

    public Skill getById(int id) {
        return repository.getById(id);
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }

    public boolean create(Skill item) {
        return repository.create(item);
    }

    public boolean update(Skill item) {
        return repository.update(item);
    }

}