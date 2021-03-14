package ca.mcgill.ecse321.arms.controller;
import ca.mcgill.ecse321.arms.dao.CustomerRepository;
import ca.mcgill.ecse321.arms.dto.CustomerDto;
import ca.mcgill.ecse321.arms.model.Customer;
import ca.mcgill.ecse321.arms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;

    @PostMapping(value = {"/customer", "/customer/"})
    public CustomerDto createCustomer(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("phonenumber") String phonenumber
    ){
        Customer customer=customerService.CreatAccount(username,  password, email, phonenumber);
        return convertToDto(customer );
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

    @DeleteMapping(value = {"/deleteCustomer", "/deleteCustomer/"})
    public void deleteCustomer(
            @RequestParam("username") String username
    ){
        customerService.deleteAccount(username);
    }

    public CustomerDto convertToDto(Customer customer){
        if(customer==null){
            throw new IllegalArgumentException("There is no such customer");
        }
        CustomerDto customerDto = new CustomerDto(customer.getUsername(),customer.getPassword(),customer.getEmail(),customer.getPhoneNumber());
        return customerDto;
    }

}
