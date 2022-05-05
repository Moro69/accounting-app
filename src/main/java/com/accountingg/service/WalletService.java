package com.accountingg.service;

import com.accountingg.entity.User;
import com.accountingg.mapper.WalletMapper;
import com.accountingg.model.CreateWalletRequest;
import com.accountingg.model.UpdateWalletRequest;
import com.accountingg.model.WalletDto;
import com.accountingg.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

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

    public WalletDto add(CreateWalletRequest request, User requester) {
        var wallet = walletMapper.toEntity(request, requester);

        walletRepository.save(wallet);

        return walletMapper.toDto(wallet);
    }

    public WalletDto update(long walletId, UpdateWalletRequest request, User requester) {
        var existing = walletRepository.findByIdAndUserId(walletId, requester.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        existing.setBalance(request.getBalance());
        existing.setName(request.getName());

        walletRepository.save(existing);

        return walletMapper.toDto(existing);
    }

    public void deleteById(long id, User user) {
        if(!walletRepository.existsByIdAndUserId(id, user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        walletRepository.deleteById(id);
    }
}
