package com.accountingg.model;

import com.accountingg.entity.WalletOperationType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletOperationDto {

    private Long id;

    private WalletOperationType type;

    private Double value;

    private Instant date;

    private String description;

    private Long walletId;

    private ExpenseCategoryDto expenseCategory;
}
