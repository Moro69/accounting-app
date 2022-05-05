package com.accountingg.model;

import com.accountingg.entity.Currency;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletDto {

    private Long id;

    private Currency currency;

    private String name;

    private Double balance;
}
