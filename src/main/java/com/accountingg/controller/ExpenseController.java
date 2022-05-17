package com.accountingg.controller;

import com.accountingg.entity.User;
import com.accountingg.entity.WalletOperationType;
import com.accountingg.model.WalletExpenseRequest;
import com.accountingg.model.WalletOperationDto;
import com.accountingg.service.WalletOperationService;
import com.accountingg.service.WalletService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/secured/wallets/{id}/expenses")
public class ExpenseController {

    private final WalletOperationService walletOperationService;
    private final WalletService walletService;

    public ExpenseController(WalletOperationService walletOperationService, WalletService walletService) {
        this.walletOperationService = walletOperationService;
        this.walletService = walletService;
    }

    @PostMapping
    public WalletOperationDto add(@PathVariable("id") long walletId,
                                  @RequestBody @Valid WalletExpenseRequest request,
                                  @AuthenticationPrincipal User requester) {
        return walletService.addExpense(walletId, request, requester);
    }

    @GetMapping
    public List<WalletOperationDto> findExpenses(@PathVariable("id") long walletId,
                                         @AuthenticationPrincipal User requester) {
        return walletOperationService.findAllByType(walletId, WalletOperationType.EXPENSE, requester);
    }
}
