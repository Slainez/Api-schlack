/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.apicrm.controller;

import fr.m2i.apicrm.dto.CustomerDTO;
import fr.m2i.apicrm.dto.CustomerMapper;
import fr.m2i.apicrm.exception.NotFoundException;
import fr.m2i.apicrm.model.Customer;
import fr.m2i.apicrm.response.ErrorResponseEntity;
import fr.m2i.apicrm.service.ICustomerService;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ben
 */
@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
     @ApiOperation(value = "Returns the list of all customers", nickname = "Get all customers", response = CustomerDTO.class)
    public ResponseEntity<Object> getAllCustomer() {
        List<Customer> customers = customerService.findAll();
        List<CustomerDTO> dtos = new ArrayList();
        for (Customer customer : customers) {
            CustomerDTO dto = CustomerMapper.buildCustomerDTO(customer);
            dtos.add(dto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return a customer", nickname = "Get a customer by id", response = CustomerDTO.class)
    public ResponseEntity<Object> getCustomerById(@PathVariable("id") String id) throws Exception {

        try {
            Long customerId = Long.parseLong(id);
            Customer founded = customerService.findById(customerId);
            CustomerDTO dto = CustomerMapper.buildCustomerDTO(founded);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NumberFormatException ne) {
            return ErrorResponseEntity.build("Parameter 'id' is not valid", 400, "/v1/customer/" + id, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException nfe) {
            return ErrorResponseEntity.build("Customer was not found", 404, "/v1/customer/" + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ErrorResponseEntity.build("An error occured", 500, "/v1/customer/" + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(value = "Create a customer", nickname = "Create a customer", response = CustomerDTO.class)
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerDTO dto) {
        try {
            Customer toCreate = CustomerMapper.buildCustomer(dto);
            Customer created = customerService.save(toCreate);
            CustomerDTO createdDTO = CustomerMapper.buildCustomerDTO(created);

            return ResponseEntity.status(HttpStatus.OK).body(createdDTO);

        } catch (Exception e) {
            return ErrorResponseEntity.build("An error occured", 500, "/v1/customers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "update a customer", nickname = "Update a customer by id", response = CustomerDTO.class)
    public ResponseEntity<Object> updateCustomer(@PathVariable("id") String id,
            @RequestBody CustomerDTO dto) throws Exception {

        try {
            Long customerId = Long.parseLong(id);
            Customer content = CustomerMapper.buildCustomer(dto);
            Customer updated = customerService.update(customerId, content);
            CustomerDTO updateDto = CustomerMapper.buildCustomerDTO(updated);

            return ResponseEntity.status(HttpStatus.OK).body(updateDto);
        } catch (NumberFormatException ne) {
            return ErrorResponseEntity.build("Parameter 'id' is not valid", 400, "/v1/customer/" + id, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException nfe) {
            return ErrorResponseEntity.build("Customer was not found", 404, "/v1/customer/" + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ErrorResponseEntity.build("An error occured", 500, "/v1/customer/" + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/customers")
    public List<Customer> getCustomerList() {
        return customerService.findAll();
    }

    @GetMapping("/customer/{id}")
    public List<Customer> getCustomerById() {
        return customerService.findAll();
    }

    @ModelAttribute("customerDTO")
    public CustomerDTO addCustomerDTO() {
        return new CustomerDTO();
    }
}
