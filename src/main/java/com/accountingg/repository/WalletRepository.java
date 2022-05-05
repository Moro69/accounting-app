package com.accountingg.repository;

import com.accountingg.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    List<Wallet> findAllByUserId(long userId);

    Optional<Wallet> findByIdAndUserId(long walletId, long userId);

    boolean existsByIdAndUserId(long walletId, long userId);

}
