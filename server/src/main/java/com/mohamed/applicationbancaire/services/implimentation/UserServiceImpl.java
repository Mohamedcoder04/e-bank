package com.mohamed.applicationbancaire.services.implimentation;

import com.mohamed.applicationbancaire.config.JwtUtils;
import com.mohamed.applicationbancaire.dtos.AccountDto;
import com.mohamed.applicationbancaire.dtos.UserDto;
import com.mohamed.applicationbancaire.dtos.auth.AuthenticationRequest;
import com.mohamed.applicationbancaire.dtos.auth.AuthenticationResponse;
import com.mohamed.applicationbancaire.models.Account;
import com.mohamed.applicationbancaire.models.Role;
import com.mohamed.applicationbancaire.models.User;
import com.mohamed.applicationbancaire.repositories.RoleRepository;
import com.mohamed.applicationbancaire.repositories.UserRepository;
import com.mohamed.applicationbancaire.services.AccountService;
import com.mohamed.applicationbancaire.services.UserService;
import com.mohamed.applicationbancaire.token.Token;
import com.mohamed.applicationbancaire.token.TokenRepository;
import com.mohamed.applicationbancaire.validator.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private static final String ROLE_USER = "ROLE_USER";
    private final UserRepository repository;
    private final AccountService accountService;
    private final ObjectsValidator<UserDto> validator;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    private final TokenRepository tokenRepository;

    private String ibanChange = "";

    @Override
    public UserDto save(UserDto dto) {
        validator.validate(dto);
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return UserDto.fromEntity(
                repository.save(UserDto.toEntity(dto))
        );
    }

    @Override
    public UserDto findById(Integer id) {
        return repository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(
                        () -> new EntityNotFoundException("aucun utilsateur n'a été trouvé avec l'ID  " + id)
                );
    }

    @Override
    @Transactional
    public List<UserDto> findAll() {
        return repository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public UserDto inValidateAccount(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));
        user.setActive(false);
        return UserDto.fromEntity(repository.save(user));
    }

    @Override
    @Transactional
    public UserDto validateAccount(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));

        if (user.getAccount() == null) {
            // create a bank account
            AccountDto account = AccountDto.builder()
                    .user(UserDto.fromEntity(user))
                    .build();
            var savedAccount = accountService.save(account);
            user.setAccount(
                    Account.builder()
                            .id(savedAccount.getId())
                            .build()
            );
        }

        user.setActive(true);
        repository.save(user);
        return UserDto.fromEntity(user);
    }

    @Override
    @Transactional
    public AuthenticationResponse register(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(findOrCreateRole(ROLE_USER));
        var savedUser = repository.save(user);
        return getAuthenticationResponse(savedUser);
    }


    private Role findOrCreateRole(String roleName) {
        Role role = roleRepository.findByName(roleName).orElse(null);
        if (role == null) {
            return roleRepository.save(
                    Role.builder()
                            .name(roleName)
                            .build()
            );
        }

        return role;
    }

    @Override
    public AuthenticationResponse autenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final User savedUser = repository.findByEmail(request.getEmail()).get();
        return getAuthenticationResponse(savedUser);
    }

    private AuthenticationResponse getAuthenticationResponse(User savedUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getId());
        claims.put("fullName", savedUser.getFirstName() + " " + savedUser.getLastName());
        String jwt = jwtUtils.generateToken(savedUser, claims);

        revokAllUserTokens(savedUser);
        var token = Token.builder()
                .user(savedUser)
                .token(jwt)
                .expired(false)
                .invoked(false)
                .build();
        tokenRepository.save(token);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    private void revokAllUserTokens(User user){
        var validToken = tokenRepository.findAllValidTokensByUser(user.getId());
        if(validToken.isEmpty()){
            return;
        }
        validToken.forEach(token -> {
            token.setExpired(true);
            token.setInvoked(true);
        });
        tokenRepository.saveAll(validToken);
    }

}
