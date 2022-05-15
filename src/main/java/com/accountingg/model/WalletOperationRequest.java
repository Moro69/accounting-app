package com.accountingg.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.Instant;

@Data
public class WalletOperationRequest {

    @Positive
    @NotNull
    private Double value;

    @NotNull
    private Instant date;

    @Size(max = 255)
    private String description;
}
