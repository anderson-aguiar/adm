package br.com.agi.adm.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_assignee")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Assignee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "assignee")
    private List<Ticket> tickets = new ArrayList<>();
}