package com.co.ke.billingservice.moneypal.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillingRequestToRMQ {
    private String emailTo;
    private String emailBy;
    private float amount;
}
