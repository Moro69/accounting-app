package com.accountingg.service;

import com.accountingg.entity.Wallet;
import com.accountingg.entity.WalletOperation;
import com.accountingg.entity.WalletOperationType;
import com.accountingg.repository.WalletOperationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WalletOperationService {

    private final WalletOperationRepository repository;

    public void addOperation(Wallet wallet, String description, Double value, WalletOperationType type) {
        var operation = new WalletOperation();
        operation.setWallet(wallet);
        operation.setDescription(description);
        operation.setType(type);
        operation.setValue(value);

        repository.save(operation);
    }
}
