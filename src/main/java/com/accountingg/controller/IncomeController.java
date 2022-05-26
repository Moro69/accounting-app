package com.accountingg.controller;

import com.accountingg.entity.User;
import com.accountingg.entity.WalletOperationType;
import com.accountingg.model.DateRange;
import com.accountingg.model.WalletOperationDto;
import com.accountingg.model.WalletOperationRequest;
import com.accountingg.service.WalletOperationService;
import com.accountingg.service.WalletService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.beans.PropertyEditorSupport;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/secured/wallets/{id}/incomes")
@Validated
public class IncomeController {

    private final WalletOperationService operationService;
    private final WalletService walletService;

    public IncomeController(WalletOperationService operationService, WalletService walletService) {
        this.operationService = operationService;
        this.walletService = walletService;
    }

    @InitBinder
    public void initBinder(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(Instant.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(Instant.ofEpochMilli(Long.parseLong(text)));
            }
        });
    }

    @PostMapping
    public WalletOperationDto addIncome(@PathVariable("id") long walletId,
                                        @RequestBody @NotNull @Valid WalletOperationRequest request,
                                        @ApiIgnore @AuthenticationPrincipal User user) {
        return walletService.addIncome(walletId, request, user);
    }

    @GetMapping
    public List<WalletOperationDto> findIncomes(@PathVariable long id,
                                                @Valid DateRange range,
                                                @ApiIgnore @AuthenticationPrincipal User user) {
        return operationService.findAllByType(id, WalletOperationType.INCOME, range, user);
    }
}
