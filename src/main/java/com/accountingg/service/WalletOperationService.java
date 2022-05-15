package com.accountingg.service;

import com.accountingg.entity.User;
import com.accountingg.entity.WalletOperationType;
import com.accountingg.mapper.WalletOperationMapper;
import com.accountingg.model.WalletExpenseRequest;
import com.accountingg.model.WalletOperationDto;
import com.accountingg.model.WalletOperationRequest;
import com.accountingg.repository.ExpenseCategoryRepository;
import com.accountingg.repository.WalletOperationRepository;
import com.accountingg.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WalletOperationService {

    private final WalletOperationRepository repository;
    private final WalletOperationMapper mapper;
    private final WalletRepository walletRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;

    public List<WalletOperationDto> findAllByType(Long walletId, WalletOperationType type, User user) {
        return repository.findAllByWalletIdAndWalletUserIdAndTypeOrderByDateDesc(walletId, user.getId(), type).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public WalletOperationDto addIncome(long walletId, WalletOperationRequest request, User user) {
        checkIfWalletExists(walletId, user);

        var entity = mapper.toEntity(walletId, request);
        repository.save(entity);

        walletRepository.increaseBalance(request.getValue(), walletId);

        return mapper.toDto(entity);
    }

    @Transactional
    public WalletOperationDto addExpense(long walletId, WalletExpenseRequest request, User user) {
        checkIfWalletExists(walletId, user);

        var category = expenseCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var entity = mapper.toEntity(walletId, request, category);
        repository.save(entity);

        walletRepository.decreaseBalance(request.getValue(), walletId);

        return mapper.toDto(entity);
    }

    private void checkIfWalletExists(long walletId, User user) {
        var walletExists = walletRepository.existsByIdAndUserId(walletId, user.getId());

        if (!walletExists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
