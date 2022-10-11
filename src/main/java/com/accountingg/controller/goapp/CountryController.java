package com.accountingg.controller.goapp;

import com.accountingg.entity.goapp.Country;
import com.accountingg.repository.goapp.CountryRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/secured/admin/countries")
public class CountryController {

    private final CountryRepository repository;

    public CountryController(CountryRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public Country create(@RequestBody Country country) {
        return repository.save(country);
    }

    @GetMapping
    public List<Country> findAll(@PageableDefault(size = 200, sort = "name") Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }
}
