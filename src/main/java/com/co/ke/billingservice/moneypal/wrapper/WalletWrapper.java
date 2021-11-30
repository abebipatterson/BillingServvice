package com.co.ke.billingservice.moneypal.wrapper;

import lombok.Data;

import java.util.UUID;
@Data
public class WalletWrapper {
    private Long id;
    private float amount;
    private UUID userid;
    private String useremail;
    private String userid2;
}
