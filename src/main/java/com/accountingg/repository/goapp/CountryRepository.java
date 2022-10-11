package com.accountingg.repository.goapp;

import com.accountingg.entity.goapp.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
