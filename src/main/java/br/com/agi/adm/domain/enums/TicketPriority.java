package br.com.agi.adm.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TicketPriority {
    LOW("BAIXA"),
    MEDIUM("MEDIA"),
    HIGH("ALTA");

    private final String value;
}
