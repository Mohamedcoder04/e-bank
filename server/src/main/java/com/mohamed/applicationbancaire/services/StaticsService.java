package com.mohamed.applicationbancaire.services;

import com.mohamed.applicationbancaire.projections.TransactionDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface StaticsService {

    List<TransactionDetails> findSumTransactionsByDate(LocalDate starDate, LocalDate endDate, Integer userId);

    List<TransactionDetails> findTransactionsByDate(LocalDate starDate, LocalDate endDate, Integer userId);

    BigDecimal getAccountBalance(Integer userId);

    BigDecimal highestTransfert(Integer userId);

    BigDecimal highestDeposit(Integer userId);
}
