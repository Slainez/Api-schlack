/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.service;

import fr.m2i.apischlack.dto.CustomerMapper;
import fr.m2i.apischlack.exception.NotFoundException;
import fr.m2i.apischlack.model.Customer;
import fr.m2i.apischlack.repository.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ben
 */
@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository repo;

    @Autowired
    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Customer> findAll() {
        return repo.findAll();
    }

    @Override
    public Customer findById(Long id) throws NotFoundException {
        return repo.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Customer with id: " + id + " was not found");
        });
    }

    @Override
    public Customer create(Customer customer) {
        return repo.save(customer);
    }

    public Customer save(Customer customer) {
        return repo.save(customer);
    }
    
    @Override
    public Customer update(Long id, Customer content) {

        Customer toUpdate = findById(id);
        toUpdate = CustomerMapper.copy(toUpdate, content);

        return repo.save(toUpdate);
    }
}
