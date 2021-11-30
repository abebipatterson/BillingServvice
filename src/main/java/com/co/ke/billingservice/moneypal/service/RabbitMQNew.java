package com.co.ke.billingservice.moneypal.service;

import com.co.ke.billingservice.moneypal.dto.BillingDto;
import com.co.ke.billingservice.moneypal.wrapper.BillingRequestToRMQ;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQNew {

    @Autowired
    private BillingService billingService;


    @RabbitListener(queues = "walletservice.queue")
    public void recievedMessage(BillingRequestToRMQ billingRequestPayloadFromRQM) {
        System.out.println("Recieved Message From RabbitMQ: " +billingRequestPayloadFromRQM);

        //create wallet
        BillingDto billingDto=new BillingDto();
       billingDto.setBillAmount(billingRequestPayloadFromRQM.getAmount());
        billingDto.setEmail_from(billingRequestPayloadFromRQM.getEmailBy());
        billingDto.setEmail_to(billingRequestPayloadFromRQM.getEmailTo());
        billingService.saveBilling(billingDto);

        System.out.println("BILLING RECORD SAVED SUCCESSFULLY");

    }

}
