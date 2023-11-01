package com.mohamed.applicationbancaire.services;

import com.mohamed.applicationbancaire.dtos.TransactionDto;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto> {
    List<TransactionDto> findAllByUserId(Integer userId);
}
