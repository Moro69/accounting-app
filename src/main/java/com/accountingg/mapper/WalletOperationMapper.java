package com.accountingg.mapper;

import com.accountingg.entity.ExpenseCategory;
import com.accountingg.entity.WalletOperation;
import com.accountingg.entity.WalletOperationType;
import com.accountingg.model.WalletExpenseRequest;
import com.accountingg.model.WalletOperationDto;
import com.accountingg.model.WalletOperationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface WalletOperationMapper {

    @Mapping(target = "walletId", source = "wallet.id")
    WalletOperationDto toDto(WalletOperation entity);

    default WalletOperation toEntity(long walletId, WalletOperationRequest request) {
        var entity = toEntity(walletId, request.getValue(), request.getDate(), request.getDescription());
        entity.setType(WalletOperationType.INCOME);

        return entity;
    }

    default WalletOperation toEntity(long walletId, WalletExpenseRequest request, ExpenseCategory category) {
        var entity = toEntity(walletId, request.getValue(), request.getDate(), request.getDescription());
        entity.setType(WalletOperationType.EXPENSE);
        entity.setExpenseCategory(category);

        return entity;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "wallet.id", source = "walletId")
    WalletOperation toEntity(Long walletId, Double value, Instant date, String description);
}
