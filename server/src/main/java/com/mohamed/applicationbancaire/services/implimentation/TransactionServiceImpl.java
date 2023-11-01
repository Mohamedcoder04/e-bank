package com.mohamed.applicationbancaire.services.implimentation;

import com.mohamed.applicationbancaire.dtos.TransactionDto;
import com.mohamed.applicationbancaire.models.TransactionType;
import com.mohamed.applicationbancaire.repositories.TransactionRepository;
import com.mohamed.applicationbancaire.services.TransactionService;
import com.mohamed.applicationbancaire.validator.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository repository;

    private final ObjectsValidator<TransactionDto> validator;

    @Override
    public TransactionDto save(TransactionDto dto) {
        validator.validate(dto);
        BigDecimal multipliyer = BigDecimal.valueOf(
                getTransactionMultiply(dto.getType())
        );
        BigDecimal amount = dto.getAmount().multiply(multipliyer);
        dto.setAmount(amount);
        return TransactionDto.fromEntity(
                repository.save(TransactionDto.toEntity(dto))
        );
    }

    @Override
    public TransactionDto findById(Integer id) {
        return repository.findById(id)
                .map(TransactionDto::fromEntity)
                .orElseThrow(
                        ()-> new EntityNotFoundException("aucune transaction n'a été trouvé avec l'ID "+ id)
                );
    }

    @Override
    public List<TransactionDto> findAll() {
        return repository.findAll()
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private int getTransactionMultiply(TransactionType type){
        return TransactionType.TRANSFERT == type ? -1 : 1;
    }

    @Override
    public List<TransactionDto> findAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }
}

