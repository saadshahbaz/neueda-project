package ca.mcgill.ecse321.arms.controller;
import ca.mcgill.ecse321.arms.ArmsApplication;
import ca.mcgill.ecse321.arms.dao.CustomerRepository;
import ca.mcgill.ecse321.arms.dto.CustomerDto;
import ca.mcgill.ecse321.arms.dto.ServiceDto;
import ca.mcgill.ecse321.arms.model.Customer;
import ca.mcgill.ecse321.arms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(value = {"/allCustomers","/allCustomers/"})
    public List<CustomerDto> getAllServices() throws IllegalArgumentException{
        List<CustomerDto> customerDtos = new ArrayList<>();
        for(Customer customer : customerService.getALlCustomers()){
            customerDtos.add(convertToDto(customer));
        }
        return customerDtos;
    }

    @PostMapping(value = {"/customer", "/customer/"})
    public CustomerDto createCustomer(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("phonenumber") String phonenumber
    ){
        Customer customer=customerService.CreatAccount(username, password, email, phonenumber);
        ArmsApplication.setCurrentuser(customer);
        return convertToDto(customer );
    }

    @PutMapping(value = {"/loginCustomer", "/loginCustomer/"})
    public CustomerDto login (
            @RequestParam("username") String username,
            @RequestParam("password") String password
    )throws IllegalArgumentException{
        Customer customer=customerService.getCustomer(username);
        if (customer.getPassword().equals(password)){
            ArmsApplication.setCurrentuser(customer);
        }else{
            throw new IllegalArgumentException("The password is incorrect");
        }
        return convertToDto(customer );
    }
    @DeleteMapping(value = {"/logoutCustomer", "/logoutCustomer/"})
    public void logout(){
        ArmsApplication.setCurrentuser(null);
    }

    @PutMapping(value = {"/updateCustomer", "/updateCustomer/"})
    public CustomerDto updateCustomer(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("phonenumber") String phonenumber
    ){
        Customer customer = customerService.updateAccount(username,password,email,phonenumber);
        return convertToDto(customer);
    }

    @GetMapping(value = {"/getCustomer", "/getCustomer/"})
    public CustomerDto getCustomer(
            @RequestParam("username") String username
    ){
        return convertToDto(customerService.getCustomer(username)) ;
    }

    @GetMapping(value = {"/getCurrentCustomer", "/getCurrentCustomer/"})
    public CustomerDto getCustomer(
    ){
        return convertToDto((Customer) ArmsApplication.getCurrentuser()) ;
    }

    @DeleteMapping(value = {"/deleteCustomer", "/deleteCustomer/"})
    public Integer deleteCustomer(){
        return customerService.deleteAccount();
    }

    public CustomerDto convertToDto(Customer customer){
        if(customer==null){
            throw new IllegalArgumentException("There is no such customer");
        }
        CustomerDto customerDto = new CustomerDto(customer.getUsername(),customer.getPassword(),customer.getEmail(),customer.getPhoneNumber());
        return customerDto;
    }

}
