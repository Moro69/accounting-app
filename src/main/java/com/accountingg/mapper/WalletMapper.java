package com.accountingg.mapper;

import com.accountingg.entity.User;
import com.accountingg.entity.Wallet;
import com.accountingg.model.CreateWalletRequest;
import com.accountingg.model.WalletDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    WalletDto toDto(Wallet wallet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "balance", source = "request.balance")
    @Mapping(target = "currency", source = "request.currency")
    @Mapping(target = "user.id", source = "user.id")
    Wallet toEntity(CreateWalletRequest request, User user);
}
