package com.accountingg.repository;

import com.accountingg.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    List<Wallet> findAllByUserId(long userId);

    Optional<Wallet> findByIdAndUserId(long walletId, long userId);

    boolean existsByIdAndUserId(long walletId, long userId);

    @Modifying
    @Query("update Wallet w set w.balance = w.balance + :value where w.id = :id")
    void increaseBalance(@Param("value") double value, @Param("id") long id);

    @Modifying
    @Query("update Wallet w set w.balance = w.balance - :value where w.id = :id")
    void decreaseBalance(@Param("value") double value, @Param("id") long id);
}
