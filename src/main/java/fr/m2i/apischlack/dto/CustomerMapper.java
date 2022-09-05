/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apischlack.dto;

import fr.m2i.apischlack.model.Customer;

/**
 *
 * @author ben
 */
public class CustomerMapper {

    public static CustomerDTO buildCustomerDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
//        String state;
//        if (customer.getState()) {
//            state = "ACTIF";
//        } else {
//            state = "INACTIF";
//        }
        // ou sinon en ternaire
        String state = customer.getState() != null && customer.getState() ? "ACTIF" : "INACTIF";
        return new CustomerDTO(customer.getId(), customer.getLastname(),
                customer.getFirstname(), customer.getCompany(), customer.getMail(),
                customer.getPhone(), customer.getAddress(), customer.getZipCode(),
                customer.getCity(), customer.getCountry(), state);

    }

    public static Customer buildCustomer(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        }
        Boolean state = "ACTIF".equals(customerDTO.getState());
        Customer newCustomer = new Customer(customerDTO.getId(), customerDTO.getLastname(), customerDTO.getFirstname(), customerDTO.getCompany(), customerDTO.getMail(), customerDTO.getPhone(), customerDTO.getAddress(), customerDTO.getZipCode(), customerDTO.getCity(), customerDTO.getCountry(), state);
        return newCustomer;
    }

    public static Customer copy(Customer customer, Customer content) {
         if (customer == null || content == null) {
            return null;
        }

        if (content.getLastname() != null) {
            customer.setLastname(content.getLastname());
        }

        if (content.getFirstname() != null) {
            customer.setFirstname(content.getFirstname());
        }

        if (content.getCompany() != null) {
            customer.setCompany(content.getCompany());
        }

        if (content.getMail() != null) {
            customer.setMail(content.getMail());
        }

        if (content.getPhone() != null) {
            customer.setPhone(content.getPhone());
        }

        if (content.getAddress() != null) {
            customer.setAddress(content.getAddress());
        }

        if (content.getZipCode() != null) {
            customer.setZipCode(content.getZipCode());
        }

        if (content.getCountry() != null) {
            customer.setCountry(content.getCountry());
        }

        if (content.getState() != null) {
            customer.setState(content.getState());
        }
        if(content.getCity() != null){
            customer.setCity(content.getCity());
        }

        return customer;
    }

}
