package com.mohamed.applicationbancaire.repositories;

import com.mohamed.applicationbancaire.models.Transaction;
import com.mohamed.applicationbancaire.models.TransactionType;
import com.mohamed.applicationbancaire.projections.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByUserId(Integer userId);

    @Query("select sum(t.amount) from Transaction t where t.user.id = :userId")
    BigDecimal findAccountBalance(@Param("userId") Integer userId);

    @Query("select max( abs( t.amount ) ) as amount from Transaction t where t.user.id = :userId and t.type = :type")
    BigDecimal findHighestAmountByTransactionType(Integer userId, TransactionType type);

    // on utilise group by t.creationDate parce que j'ai fait select t.creationDate
    @Query("select t.transactionDate as transactionDate, sum(t.amount) as amount from Transaction t where t.user.id = :userId and (t.creationDate between :start and :end) group by t.transactionDate")
    List<TransactionDetails> findSumTransactionByDate(LocalDateTime start, LocalDateTime end, Integer userId);

    @Query("select t.transactionDate as transactionDate , t.amount as amount, t.type as type  from Transaction t where t.user.id = :userId and (t.creationDate between :start and :end) group by t.transactionDate, t.amount, t.type")
    List<TransactionDetails> findAllTransactionsByDate(LocalDateTime start, LocalDateTime end, Integer userId);
}
