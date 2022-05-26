package com.accountingg.model;

import lombok.Data;

import java.time.Instant;

@Data
public class DateRange {

    private Instant from;

    private Instant to;
}
