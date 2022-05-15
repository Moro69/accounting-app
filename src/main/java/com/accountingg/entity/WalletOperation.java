package com.accountingg.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "wallet_operations")
@Data
public class WalletOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private WalletOperationType type;

    @Column
    private Double value;

    @Column
    private Instant date;

    @Column(name = "created_date")
    @CreationTimestamp
    private Instant createdDate;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @OneToOne
    @JoinColumn(name = "expense_category_id")
    private ExpenseCategory expenseCategory;
}
