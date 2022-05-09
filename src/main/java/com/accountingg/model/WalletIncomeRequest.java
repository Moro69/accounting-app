package com.accountingg.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class WalletIncomeRequest {

    @Positive
    @NotNull
    private Double value;

    @Size(max = 255)
    private String description;
}
