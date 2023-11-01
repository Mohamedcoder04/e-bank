package com.mohamed.applicationbancaire.services.implimentation;

import com.mohamed.applicationbancaire.models.TransactionType;
import com.mohamed.applicationbancaire.projections.TransactionDetails;
import com.mohamed.applicationbancaire.repositories.TransactionRepository;
import com.mohamed.applicationbancaire.services.StaticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StaticsServiceImpl implements StaticsService {

    private final TransactionRepository transactionRepository;

    @Override
    public BigDecimal getAccountBalance(Integer userId) {
        return transactionRepository.findAccountBalance(userId);
    }

    @Override
    public List<TransactionDetails> findSumTransactionsByDate(LocalDate starDate, LocalDate endDate, Integer userId) {
        // pour couvrir toute la journéé on fait
        LocalDateTime start = LocalDateTime.of(starDate, LocalTime.of(0,0,0));
        LocalDateTime end = LocalDateTime.of(endDate, LocalTime.of(23,59,59));
        return transactionRepository.findSumTransactionByDate(start, end, userId);
    }

    @Override
    public List<TransactionDetails> findTransactionsByDate(LocalDate starDate, LocalDate endDate, Integer userId) {

        LocalDateTime start = LocalDateTime.of(starDate, LocalTime.of(0,0,0));
        LocalDateTime end = LocalDateTime.of(endDate, LocalTime.of(23,59,59));

        return transactionRepository.findAllTransactionsByDate(start, end, userId);
    }

    @Override
    public BigDecimal highestTransfert(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId , TransactionType.TRANSFERT);
    }

    @Override
    public BigDecimal highestDeposit(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.DEPOSIT);
    }


}
