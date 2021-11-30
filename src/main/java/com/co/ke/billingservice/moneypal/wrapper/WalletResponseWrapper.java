package com.co.ke.billingservice.moneypal.wrapper;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class WalletResponseWrapper {
    private int responseCode;
    private String responseMessage;
    private Date date=new Date();
    private List<WalletWrapper> responseBody;
}
