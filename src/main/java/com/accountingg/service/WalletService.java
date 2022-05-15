package com.accountingg.service;

import com.accountingg.entity.User;
import com.accountingg.entity.WalletOperationType;
import com.accountingg.mapper.WalletMapper;
import com.accountingg.model.CreateWalletRequest;
import com.accountingg.model.UpdateWalletRequest;
import com.accountingg.model.WalletDto;
import com.accountingg.model.WalletOperationRequest;
import com.accountingg.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;
    private final WalletOperationService walletOperationService;

    public List<WalletDto> findAll(User user) {
        return walletRepository.findAllByUserId(user.getId()).stream()
                .map(walletMapper::toDto)
                .collect(Collectors.toList());
    }

    public WalletDto findById(long walletId, User requester) {
        return walletRepository.findByIdAndUserId(walletId, requester.getId())
                .map(walletMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @Transactional
    public WalletDto add(CreateWalletRequest request, User requester) {
        var wallet = walletMapper.toEntity(request, requester);

        walletRepository.save(wallet);

        addOperation(request.getBalance(), requester, wallet.getId());

        return walletMapper.toDto(wallet);
    }

    private void addOperation(Double value, User requester, Long walletId) {
        var operationRequest = new WalletOperationRequest();
        operationRequest.setDate(Instant.now());
        operationRequest.setDescription("Wallet creation");
        operationRequest.setValue(value);

        walletOperationService.addIncome(walletId, operationRequest, requester);
    }

    @Transactional
    public WalletDto update(long walletId, UpdateWalletRequest request, User requester) {
        var existing = walletRepository.findByIdAndUserId(walletId, requester.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        existing.setName(request.getName());

        walletRepository.save(existing);

        return walletMapper.toDto(existing);
    }

    @Transactional
    public void deleteById(long id, User user) {
        if(!walletRepository.existsByIdAndUserId(id, user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        walletRepository.deleteById(id);
    }
}
