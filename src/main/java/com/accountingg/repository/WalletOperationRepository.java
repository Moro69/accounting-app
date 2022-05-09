package com.accountingg.repository;

import com.accountingg.entity.WalletOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletOperationRepository extends JpaRepository<WalletOperation, Long> {
}
