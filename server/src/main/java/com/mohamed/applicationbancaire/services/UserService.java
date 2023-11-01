package com.mohamed.applicationbancaire.services;

import com.mohamed.applicationbancaire.dtos.UserDto;
import com.mohamed.applicationbancaire.dtos.auth.AuthenticationRequest;
import com.mohamed.applicationbancaire.dtos.auth.AuthenticationResponse;

public interface UserService extends AbstractService<UserDto> {

    // valider un compte d'un user
    UserDto inValidateAccount(Integer id);

    // valider un compte d'un user
    UserDto validateAccount(Integer id);

    AuthenticationResponse register(UserDto dto);

    AuthenticationResponse autenticate(AuthenticationRequest request);

}
