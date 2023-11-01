package com.mohamed.applicationbancaire.services.implimentation;

import com.mohamed.applicationbancaire.dtos.ContactDto;
import com.mohamed.applicationbancaire.repositories.ContactRepository;
import com.mohamed.applicationbancaire.services.ContactService;
import com.mohamed.applicationbancaire.validator.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;
    private final ObjectsValidator<ContactDto> validator;

    @Override
    public ContactDto save(ContactDto dto) {
        validator.validate(dto);
        return ContactDto.fromEntity(
                repository.save(ContactDto.toEntity(dto))
        );
    }

    @Override
    public ContactDto findById(Integer id) {

        return repository.findById(id)
                .map(ContactDto::fromEntity)
                .orElseThrow(
                        ()-> new EntityNotFoundException("aucun contact n'a été trouvé avec l'ID "+ id)
                );
    }

    @Override
    public List<ContactDto> findAll() {
        return repository.findAll().stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<ContactDto> findAllByUseId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }
}
