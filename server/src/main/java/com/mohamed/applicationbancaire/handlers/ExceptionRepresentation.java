package com.mohamed.applicationbancaire.handlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY) // c-v-d si un champs est vide ne l'affiche pas que c'est vide
// si validationErrors est vide alors il va afficher que errorMessage et errorSource
public class ExceptionRepresentation {

    private String errorMessage;

    private String errorSource;

    private Set<String> validationErrors;

}
