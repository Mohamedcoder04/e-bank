package com.mohamed.applicationbancaire.services;

import com.mohamed.applicationbancaire.dtos.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto> {

    List<ContactDto> findAllByUseId(Integer userId);
}
