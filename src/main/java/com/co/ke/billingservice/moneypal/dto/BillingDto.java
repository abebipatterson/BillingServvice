package com.co.ke.billingservice.moneypal.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class BillingDto {
//    private UUID userId_from;
//    private UUID userId_to;
    private String email_from;
    private String email_to;
    private float billAmount;
}
