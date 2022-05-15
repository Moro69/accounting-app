package com.accountingg.controller;

import com.accountingg.mapper.ExpenseCategoryMapper;
import com.accountingg.model.ExpenseCategoryDto;
import com.accountingg.repository.ExpenseCategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/secured/expense-categories")
public class ExpenseCategoryController {

    private final ExpenseCategoryMapper mapper;
    private final ExpenseCategoryRepository repository;

    public ExpenseCategoryController(ExpenseCategoryMapper mapper, ExpenseCategoryRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @GetMapping
    public List<ExpenseCategoryDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
