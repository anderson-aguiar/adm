package br.com.agi.adm.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TicketStatus {

    OPEN("ABERTO"),
    IN_PROGRESS("EM_PROGRESSO"),
    CLOSED("FECHADO");

    private final String value;

}
