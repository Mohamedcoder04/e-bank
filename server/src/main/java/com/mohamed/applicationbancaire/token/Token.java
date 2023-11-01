package com.mohamed.applicationbancaire.token;

import com.mohamed.applicationbancaire.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue
    private Integer id;

    private String token;

    private boolean expired;

    private boolean invoked;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

}
