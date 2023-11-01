package com.mohamed.applicationbancaire.dtos;

import com.mohamed.applicationbancaire.models.Account;
import com.mohamed.applicationbancaire.models.Role;
import com.mohamed.applicationbancaire.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {

    private Integer id;

    @NotNull(message = "Le prenom ne doit pas etre vide")
    @NotEmpty(message = "Le prenom ne doit pas etre vide")
    @NotBlank(message = "Le prenom ne doit pas etre vide")
    private String firstName;

    @NotNull(message = "Le nom ne doit pas etre vide")
    @NotEmpty(message = "Le nom ne doit pas etre vide")
    @NotBlank(message = "Le nom ne doit pas etre vide")
    private String lastName;

    @NotNull(message = "L'email ne doit pas etre vide")
    @NotEmpty(message = "L'email ne doit pas etre vide")
    @NotBlank(message = "L'email ne doit pas etre vide")
    @Email(message = "L'email n'est conforme")
    private String email;

    private Role role;

    @NotNull(message = "Le mot de passe ne doit pas etre vide")
    @NotEmpty(message = "Le mot de passe ne doit pas etre vide")
    @NotBlank(message = "Le mot de passe ne doit pas etre vide")
    @Size(min = 8, max = 16, message = "Le mot de passe doit etre entre 8 et 16 caracteres")
    private String password;

    private boolean active;

    private AddressDto addressDto;

    private String iban;


    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .active(user.isActive())
                .role(user.getRole())
                .addressDto(user.getAdress() == null ? null : AddressDto.fromEntity(user.getAdress()))
                .iban(user.getAccount() == null ? "" : user.getAccount().getIban())
                .build();
    }

    public static User toEntity(UserDto user){
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .adress(user.getAddressDto() == null ? null : AddressDto.toEntity(user.getAddressDto()))
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
