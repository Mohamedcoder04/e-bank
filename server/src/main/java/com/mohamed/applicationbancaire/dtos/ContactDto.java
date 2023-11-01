package com.mohamed.applicationbancaire.dtos;

import com.mohamed.applicationbancaire.models.Contact;
import com.mohamed.applicationbancaire.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor
public class ContactDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String iban;

    private Integer userId;

    public static ContactDto fromEntity(Contact contact){
        return ContactDto.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .iban(contact.getIban())
                .email(contact.getEmail())
                .userId(contact.getUser().getId())
                .build();
    }

    public static Contact toEntity(ContactDto contact){
        return Contact.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .iban(contact.getIban())
                .email(contact.getEmail())
                .user(User.builder()
                        .id(contact.getUserId())
                        .build()
                )
                .build();
    }

}
