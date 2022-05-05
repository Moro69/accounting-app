package com.accountingg.model;

import com.accountingg.entity.Currency;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
public class CreateWalletRequest {

    @NotNull
    private Currency currency;

    @PositiveOrZero
    @NotNull
    private Double balance;

    @NotBlank
    @Size(max = 255)
    private String name;
}
