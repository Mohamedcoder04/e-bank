package com.mohamed.applicationbancaire.services.implimentation;

import com.mohamed.applicationbancaire.dtos.AccountDto;
import com.mohamed.applicationbancaire.exeptions.OperationNotPermittedException;
import com.mohamed.applicationbancaire.repositories.AccountRepository;
import com.mohamed.applicationbancaire.services.AccountService;
import com.mohamed.applicationbancaire.validator.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final ObjectsValidator<AccountDto> validator;

    @Override
    public AccountDto save(AccountDto dto) {
        // si on ne veut pas modifier un compte
        if(dto.getId() != null){
            throw new OperationNotPermittedException(
                    "Account cannot be updated",
                    "Save account",
                    "Account service ",
                    "Update not permitted"
            );
        }
        validator.validate(dto);
        boolean userHasAlreadyAnAccount = repository.findByUserId(dto.getUser().getId()).isPresent();
        if(userHasAlreadyAnAccount && dto.getUser().isActive() ){
            throw new OperationNotPermittedException(
                    "The selected user has already an active account",
                    "Create account",
                    "Account service",
                    "Account creation"
            );
        }
        dto.setIban(generateRandomIban());

        return AccountDto.fromEntity(
                repository.save(AccountDto.toEntity(dto))
        );
    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(
                        ()-> new EntityNotFoundException("aucun account n'a été trouvé avec l'ID "+id)
                );
    }

    @Override
    public List<AccountDto> findAll() {
        return repository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private String generateRandomIban(){
        String iban = Iban.random(CountryCode.DE).toFormattedString();

        boolean ibanExists = repository.findByIban(iban).isPresent();
        if(ibanExists){
            generateRandomIban();
        }
        return iban;
    }
}
