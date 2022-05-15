package com.accountingg.mapper;

import com.accountingg.entity.ExpenseCategory;
import com.accountingg.model.ExpenseCategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseCategoryMapper {

    ExpenseCategoryDto toDto(ExpenseCategory entity);
}
