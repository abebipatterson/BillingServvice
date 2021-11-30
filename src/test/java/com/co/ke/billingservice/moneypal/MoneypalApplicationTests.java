package com.co.ke.billingservice.moneypal;

import com.co.ke.billingservice.moneypal.model.Billing;
import com.co.ke.billingservice.moneypal.repository.BillingRepository;
import com.co.ke.billingservice.moneypal.service.BillingService;
import com.co.ke.billingservice.moneypal.wrapper.GeneralResponseWrapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class MoneypalApplicationTests {


    @MockBean
     private BillingRepository billingRepository;

    @Autowired
    private BillingService billingService;

    @Test
    void contextLoads() {
    }

    @Test
    void saveBillingDetailsTest(){

    }

    @Test
    void fetchAllBillingsTest(){
        when(billingRepository.findAll()).thenReturn(Stream.of(

                new Billing(null,"aaaa@gmail.com","bbb@gmail.com",345678899, (float)560.00,true,new Date()),
                new Billing(null,"aaaa@gmail.com","bbb@gmail.com",345678899, (float)560.00,true,new Date()))
                .collect(Collectors.toList()));
        GeneralResponseWrapper g=billingService.fetchAllBillingsRecord();
        List<Billing> p= (List<Billing>) g.getResponseBody();
        assertEquals(2,p.size());

    }

}
