package com.co.ke.billingservice.moneypal.controller;

import com.co.ke.billingservice.moneypal.dto.BillingDto;
import com.co.ke.billingservice.moneypal.service.BillingService;
import com.co.ke.billingservice.moneypal.wrapper.GeneralResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billing")
public class BillingController {
@Autowired
private BillingService billingService;
    //fetch all billings
    @GetMapping("/fetchAll")
    public ResponseEntity<GeneralResponseWrapper> getAllBillings(){
        GeneralResponseWrapper generalResponseWrapper=billingService.fetchAllBillingsRecord();
        return ResponseEntity.ok().body(generalResponseWrapper);
    }

    //fetch userfrom billings

    //fetch user2 billings

    //save billing records
    @PostMapping("/save")
    public ResponseEntity<GeneralResponseWrapper> saveBillingRecords(@RequestBody BillingDto billingDto){
        GeneralResponseWrapper generalResponseWrapper=billingService.doBilling(billingDto);
        return ResponseEntity.ok().body(generalResponseWrapper);
    }

}
