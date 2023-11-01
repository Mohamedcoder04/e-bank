package com.mohamed.applicationbancaire.dtos;

import com.mohamed.applicationbancaire.models.Address;
import com.mohamed.applicationbancaire.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AddressDto {

    private Integer id;

    private String street;

    private Integer houseNumber;

    private Integer zipCode;

    private String city;

    private String country;

    private Integer userId;

    public static AddressDto fromEntity(Address adress){
        return AddressDto.builder()
                .id(adress.getId())
                .street(adress.getStreet())
                .houseNumber(adress.getHouseNumber())
                .zipCode(adress.getZipCode())
                .city(adress.getCity())
                .country(adress.getCountry())
                .userId(adress.getUser().getId())
                .build();
    }

    public static Address toEntity(AddressDto dto){
        return Address.builder()
                .id(dto.getId())
                .street(dto.getStreet())
                .houseNumber(dto.getHouseNumber())
                .zipCode(dto.getZipCode())
                .city(dto.getCity())
                .country(dto.getCountry())
                .user(User.builder()
                        .id(dto.getUserId())
                        .build()
                )
                .build();
    }
}
