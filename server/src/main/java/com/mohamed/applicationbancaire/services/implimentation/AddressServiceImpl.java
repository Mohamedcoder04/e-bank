package com.mohamed.applicationbancaire.services.implimentation;

import com.mohamed.applicationbancaire.dtos.AddressDto;
import com.mohamed.applicationbancaire.repositories.AddressRepository;
import com.mohamed.applicationbancaire.services.AddressService;
import com.mohamed.applicationbancaire.validator.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final ObjectsValidator<AddressDto> validator;

    @Override
    public AddressDto save(AddressDto dto) {
        validator.validate(dto);
        return AddressDto.fromEntity(repository.save(AddressDto.toEntity(dto)));
    }

    @Override
    public AddressDto findById(Integer id) {
        return repository.findById(id).map(AddressDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("aucune adresse n'a été trouvé avec l'ID "+id));
    }

    @Override
    public List<AddressDto> findAll() {
        return repository.findAll().stream()
                .map(AddressDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
