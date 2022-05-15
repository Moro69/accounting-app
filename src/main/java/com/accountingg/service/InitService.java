package com.accountingg.service;

import com.accountingg.entity.ExpenseCategory;
import com.accountingg.repository.ExpenseCategoryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class InitService {

    private final ExpenseCategoryRepository expenseCategoryRepository;

    public InitService(ExpenseCategoryRepository expenseCategoryRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
    }

    @PostConstruct
    public void init() {
        addExpenseCategories();
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
