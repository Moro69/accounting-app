package com.accountingg.service;

import com.accountingg.entity.ExpenseCategory;
import com.accountingg.entity.User;
import com.accountingg.entity.UserRole;
import com.accountingg.repository.ExpenseCategoryRepository;
import com.accountingg.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.List;

@Service
public class InitService {

    private final ExpenseCategoryRepository expenseCategoryRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public InitService(ExpenseCategoryRepository expenseCategoryRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        addExpenseCategories();

        initAdminUser();
    }

    private void initAdminUser() {
        if (!userRepository.existsByRole(UserRole.ROLE_ADMIN)) {
            final User user = new User();
            user.setEmail("admin@admin");
            user.setRole(UserRole.ROLE_ADMIN);
            user.setPassword(passwordEncoder.encode("admin123"));
            user.setCreatedDate(Instant.now());

            userRepository.save(user);
        }
    }

    private void addExpenseCategories() {
        if (expenseCategoryRepository.count() == 0) {
            expenseCategoryRepository.saveAll(List.of(
                    new ExpenseCategory("Purchases", "https://cdn-icons-png.flaticon.com/512/3902/3902757.png"),
                    new ExpenseCategory("Grocery", "https://cdn-icons-png.flaticon.com/512/3081/3081905.png"),
                    new ExpenseCategory("House", "https://cdn-icons-png.flaticon.com/512/2413/2413379.png"),
                    new ExpenseCategory("Transport", "https://cdn-icons-png.flaticon.com/512/6254/6254009.png"),
                    new ExpenseCategory("Health", "https://cdn-icons-png.flaticon.com/512/2966/2966470.png"),
                    new ExpenseCategory("Entertainment", "https://cdn-icons-png.flaticon.com/512/3163/3163487.png"),
                    new ExpenseCategory("Traveling", "https://cdn-icons-png.flaticon.com/512/2798/2798100.png"),
                    new ExpenseCategory("Other", "https://cdn-icons-png.flaticon.com/512/4564/4564311.png")
            ));
        }
    }
}
