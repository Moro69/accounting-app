package com.accountingg.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class WalletExpenseRequest extends WalletOperationRequest {

    @NotNull
    private Long categoryId;
}