package services;

import models.Customer;
import repositories.CustomerRepository;

import java.util.List;

public class CustomerService implements BasicService<Customer> {
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> getAll() {
        return repository.getAll();
    }

    public Customer getById(int id) {
        return repository.getById(id);
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }

    public boolean create(Customer item) {
        return repository.create(item);
    }

    public boolean update(Customer item) {
        return repository.update(item);
    }

}