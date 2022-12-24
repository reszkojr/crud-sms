package com.reszkojr.dsmeta.services;

import com.reszkojr.dsmeta.entities.Sale;
import com.reszkojr.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalUnit;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public Page<Sale> findSales(String minDate, String maxDate, Pageable pageable) {
        LocalDate now = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate min = (minDate.equals("") ? now.minusYears(1) : LocalDate.parse(minDate));
        LocalDate max = (maxDate.equals("") ? now : LocalDate.parse(maxDate));

        return repository.findSales(min, max, pageable);
    }
}
