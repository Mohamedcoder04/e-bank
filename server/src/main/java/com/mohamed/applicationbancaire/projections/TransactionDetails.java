package com.mohamed.applicationbancaire.projections;

import com.mohamed.applicationbancaire.models.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TransactionDetails {

    LocalDate getTransactionDate();

    BigDecimal getAmount();

    TransactionType getType();

}
