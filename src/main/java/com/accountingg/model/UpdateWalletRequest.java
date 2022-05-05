package com.accountingg.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateWalletRequest {

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotNull
    private Double balance;
}
