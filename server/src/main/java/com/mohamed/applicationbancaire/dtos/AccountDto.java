package com.mohamed.applicationbancaire.dtos;

import com.mohamed.applicationbancaire.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
@AllArgsConstructor
public class AccountDto {

    private Integer id;

    private String iban;

    private UserDto user;

    public static AccountDto fromEntity(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .iban(account.getIban())
                .user(UserDto.fromEntity(account.getUser()))
                .build();
    }

    public static Account toEntity(AccountDto dto){
        return Account.builder()
                .id(dto.getId())
                .iban(dto.getIban())
                .user(UserDto.toEntity(dto.getUser()))
                .build();
    }
}
