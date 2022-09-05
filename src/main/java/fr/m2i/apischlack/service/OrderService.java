/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.service;

import fr.m2i.apischlack.dto.OrderMapper;
import fr.m2i.apischlack.exception.NotFoundException;
import fr.m2i.apischlack.model.Order;
import fr.m2i.apischlack.repository.OrderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ben
 */
@Service
public class OrderService implements IOrderService{
    private final OrderRepository repo;
    
    @Autowired
    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }
    @Override
    public List<Order> findAll() {
        return repo.findAll();
    }

    @Override
    public Order findById(Long id) throws NotFoundException{
        return repo.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Customer with id: " + id + " was not found");
        });
    }

    @Override
    public Order save(Order order) {
        return repo.save(order);
    }

    @Override
    public Order update(Long id, Order content) {
        Order toUpdate = findById(id);
        toUpdate = OrderMapper.copy(toUpdate, content);
        return save(toUpdate);
    }

    @Override
    public void delete(Long id) {
        Order toDelete = findById(id);
        repo.delete(toDelete);
    }
    
    
    
}
