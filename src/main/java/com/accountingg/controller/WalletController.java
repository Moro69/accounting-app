package com.accountingg.controller;

import com.accountingg.entity.User;
import com.accountingg.model.CreateWalletRequest;
import com.accountingg.model.UpdateWalletRequest;
import com.accountingg.model.WalletDto;
import com.accountingg.service.WalletOperationService;
import com.accountingg.service.WalletService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/secured/wallets")
@Validated
public class WalletController {

    private final WalletService walletService;
    private final WalletOperationService walletOperationService;

    public WalletController(WalletService walletService, WalletOperationService walletOperationService) {
        this.walletService = walletService;
        this.walletOperationService = walletOperationService;
    }

    @GetMapping
    public List<WalletDto> getAll(@ApiIgnore @AuthenticationPrincipal User user) {
        return walletService.findAll(user);
    }

    @GetMapping("/{id}")
    public WalletDto getById(@PathVariable long id, @ApiIgnore @AuthenticationPrincipal User user) {
        return walletService.findById(id, user);
    }

    @PostMapping
    public WalletDto add(@RequestBody @NotNull @Valid CreateWalletRequest request,
                         @ApiIgnore @AuthenticationPrincipal User user) {
        return walletService.add(request, user);
    }

    @PutMapping("/{id}")
    public WalletDto update(@PathVariable long id,
                            @RequestBody @NotNull @Valid UpdateWalletRequest request,
                            @ApiIgnore @AuthenticationPrincipal User user) {
        return walletService.update(id, request, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id,
                       @ApiIgnore @AuthenticationPrincipal User user) {
        walletService.deleteById(id, user);
    }
}
