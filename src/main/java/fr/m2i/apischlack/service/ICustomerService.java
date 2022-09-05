/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.service;

import fr.m2i.apischlack.model.Customer;
import java.util.List;

/**
 *
 * @author ben
 */
public interface ICustomerService {

    List<Customer> findAll();

    Customer findById(Long id);

    Customer create(Customer customer);
    Customer save(Customer customer);
    Customer update(Long id,Customer content);
}
