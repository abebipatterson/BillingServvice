package com.co.ke.billingservice.moneypal.service;

import com.co.ke.billingservice.moneypal.dto.BillingDto;
import com.co.ke.billingservice.moneypal.model.Billing;
import com.co.ke.billingservice.moneypal.repository.BillingRepository;
import com.co.ke.billingservice.moneypal.wrapper.GeneralResponseWrapper;
import com.co.ke.billingservice.moneypal.wrapper.WalletResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class BillingService {
    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${walletServiceUrl}")
    public String walletServiceUrl;

    public GeneralResponseWrapper saveBilling(BillingDto billingDto){
        GeneralResponseWrapper generalResponseWrapper=new GeneralResponseWrapper();
        try{

                Billing billing=new Billing();
                int invoiceNumber=generateInvoiceNumber();
                billing.setStatus(true);
                billing.setBillAmount(billingDto.getBillAmount());
                billing.setInvoiceNumber(invoiceNumber);
                billing.setUserEmail_from(billingDto.getEmail_from());
                billing.setUserEmail_to(billingDto.getEmail_to());

                //Saving the billing details
                billingRepository.save(billing);

                generalResponseWrapper.setResponseBody(billing);
                generalResponseWrapper.setResponseCode(200);

        }
        catch(Exception ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
            generalResponseWrapper.setResponseCode(500);
            generalResponseWrapper.setResponseMessage("Internal Server Error");
            generalResponseWrapper.setResponseBody(null);

        }
        return  generalResponseWrapper;
    }


    // do billing
    public GeneralResponseWrapper doBilling(BillingDto billingDto){
        GeneralResponseWrapper generalResponseWrapper=new GeneralResponseWrapper();
        try{
            String url=walletServiceUrl + "/wallet-billing/"+billingDto.getEmail_from()+"/"+billingDto.getEmail_to()+"/"+billingDto.getBillAmount();
            ResponseEntity<WalletResponseWrapper> generalResponseWrapper1 = restTemplate.getForEntity(url,WalletResponseWrapper.class);
            int res=generalResponseWrapper1.getStatusCodeValue();
            if(res == 200 && generalResponseWrapper1.getBody() !=null){
                WalletResponseWrapper walletResponseWrapper=generalResponseWrapper1.getBody();
                int ress=walletResponseWrapper.getResponseCode();
                // check if Wallet billing operation was succesfull,,, RSP CODE ::: 200
                Billing billing=new Billing();
                int invoiceNumber=generateInvoiceNumber();
                if(ress==200){
                billing.setStatus(true);
                    generalResponseWrapper.setResponseCode(ress);
                    generalResponseWrapper.setResponseMessage("Payment made successful");
                }
                else{
                    billing.setStatus(false);
                    log.info("Billing Failed " );
                    generalResponseWrapper.setResponseCode(ress);
                    generalResponseWrapper.setResponseMessage(walletResponseWrapper.getResponseMessage());
                }

                billing.setBillAmount(billingDto.getBillAmount());
                billing.setInvoiceNumber(invoiceNumber);
                billing.setUserEmail_from(billingDto.getEmail_from());
                billing.setUserEmail_to(billingDto.getEmail_to());

                //Saving the billing details
                billingRepository.save(billing);

                generalResponseWrapper.setResponseBody(billing);

            }
            else{
                log.info("Billing Failed" );
                generalResponseWrapper.setResponseCode(400);
                generalResponseWrapper.setResponseMessage("Billing Failed with response code of : "+res);
                generalResponseWrapper.setResponseBody(null);
            }

        }
        catch(Exception ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
            generalResponseWrapper.setResponseCode(500);
            generalResponseWrapper.setResponseMessage("Internal Server Error");
            generalResponseWrapper.setResponseBody(null);

        }
        return  generalResponseWrapper;
    }

    public int generateInvoiceNumber(){
        int res=0;
        Random rnd=new Random();
        res=rnd.nextInt(999999999);
        String val=String.format("%09d",res);
        return Integer.parseInt(val);
    }

    public GeneralResponseWrapper fetchAllBillingsRecord(){
        GeneralResponseWrapper generalResponseWrapper=new GeneralResponseWrapper();
        try{
            List<Billing> billings= billingRepository.findAll();

            generalResponseWrapper.setResponseCode(200);
            generalResponseWrapper.setResponseMessage("Successfully fetched");
            generalResponseWrapper.setResponseBody(billings);
        }
        catch(Exception ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
            generalResponseWrapper.setResponseCode(500);
            generalResponseWrapper.setResponseMessage("Internal Server Error");

        }
        return  generalResponseWrapper;
    }

}
