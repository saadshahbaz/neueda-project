package ca.mcgill.ecse321.arms.controller;
import ca.mcgill.ecse321.arms.dao.BillRepository;
import ca.mcgill.ecse321.arms.dto.BillDto;
import ca.mcgill.ecse321.arms.model.Bill;
import ca.mcgill.ecse321.arms.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class BillController {
    @Autowired
    BillService billService;
    @Autowired
    BillRepository billRepository;
    @PostMapping(value = {"/bill", "/bill/"})
    public BillDto createBill(
            @RequestParam("customer")  String customer,
            @RequestParam("amount") int amount,
            @RequestParam("BillNo") int billNo
    ){
        Bill bill=billService.createBill(customer,billNo,amount);
        return convertToDto(bill);
    }

    @PostMapping(value = {"/payBill", "/payBill/"})
    public Bill payBill(
            @RequestParam("BillNo") int billNo
    ){
        return billService.payBill(billNo);
    }

    @GetMapping(value = {"/getBill", "/getBill/"})
    public BillDto getBill(
            @RequestParam("BillNo") int billNo
    ){
        Bill bill=billService.getBill(billNo);
        return convertToDto(bill);
    }

    @GetMapping(value = {"/getBillsByCustomer", "/getBillsByCustomer/"})
    public List<BillDto> getBillsByCustomer(
            @RequestParam("username") String username
    ){
        List<Bill> bills=billService.getBillsByCustomer(username);
        List<BillDto> billDtos= new ArrayList<>();
        for(Bill bill:bills){
            BillDto billDto=convertToDto(bill);
            billDtos.add(billDto);
        }
        return billDtos;
    }



    public BillDto convertToDto(Bill bill){
        if(bill==null){
            throw new IllegalArgumentException("There is no such bill");
        }
        BillDto billDto = new BillDto(bill.getCustomer().getUsername(),bill.isIsPaid(),bill.getAmount(),bill.getBillNo());
        return billDto;
    }

}

