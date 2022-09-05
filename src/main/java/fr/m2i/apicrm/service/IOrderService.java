/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apicrm.service;

import fr.m2i.apicrm.model.Order;
import java.util.List;

/**
 *
 * @author ben
 */
public interface IOrderService {
    List<Order> findAll();
    Order findById(Long id);
    Order save(Order order);
    Order update(Long id,Order content);
    void delete(Long id);
}
