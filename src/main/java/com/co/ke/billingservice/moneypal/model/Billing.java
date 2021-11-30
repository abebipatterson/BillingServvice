package com.co.ke.billingservice.moneypal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Billing {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private UUID id;
//    private UUID userId_from;
//    private UUID userId_to;
    private String userEmail_from;
    private String userEmail_to;
    private int invoiceNumber;
    private float billAmount;
    private boolean status=false;
    private Date date=new Date();
}
