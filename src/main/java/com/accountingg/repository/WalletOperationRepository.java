package com.accountingg.repository;

import com.accountingg.entity.WalletOperation;
import com.accountingg.entity.WalletOperationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletOperationRepository extends JpaRepository<WalletOperation, Long> {

    List<WalletOperation> findAllByWalletIdAndWalletUserIdAndTypeOrderByDateDesc(Long walletId, Long userId, WalletOperationType type);

    void deleteAllByWalletId(long walletId);
}
