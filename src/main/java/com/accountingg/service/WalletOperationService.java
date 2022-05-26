package com.accountingg.service;

import com.accountingg.entity.User;
import com.accountingg.entity.WalletOperation;
import com.accountingg.entity.WalletOperationType;
import com.accountingg.mapper.WalletOperationMapper;
import com.accountingg.model.DateRange;
import com.accountingg.model.WalletExpenseRequest;
import com.accountingg.model.WalletOperationDto;
import com.accountingg.model.WalletOperationRequest;
import com.accountingg.repository.ExpenseCategoryRepository;
import com.accountingg.repository.WalletOperationRepository;
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
    private final ExpenseCategoryRepository expenseCategoryRepository;

    public List<WalletOperationDto> findAllByType(Long walletId, WalletOperationType type, DateRange range, User user) {
        List<WalletOperation> operations;

        if (range != null && range.getFrom() != null && range.getTo() != null) {
            operations = repository.findAllByWalletIdAndWalletUserIdAndTypeAndDateBetweenOrderByDateDesc(walletId, user.getId(), type, range.getFrom(), range.getTo());
        } else {
            operations = repository.findAllByWalletIdAndWalletUserIdAndTypeOrderByDateDesc(walletId, user.getId(), type);
        }

        return operations.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public WalletOperationDto addIncome(long walletId, WalletOperationRequest request) {
        var entity = mapper.toEntity(walletId, request);
        repository.save(entity);

        return mapper.toDto(entity);
    }

    @Transactional
    public WalletOperationDto addExpense(long walletId, WalletExpenseRequest request) {
        var category = expenseCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var entity = mapper.toEntity(walletId, request, category);
        repository.save(entity);

        return mapper.toDto(entity);
    }

    @Transactional
    public void deleteByWallet(long walletId) {
        repository.deleteAllByWalletId(walletId);
    }
}
