package com.accountingg.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpenseCategoryDto {

    private Long id;

    private String name;

    private String icon;
}
