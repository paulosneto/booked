package com.agenda.book.domains;

import com.agenda.book.dtos.AgendaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgenda;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;


    public Agenda(AgendaDTO dto){
        this.descricao = dto.descricao();
        this.dataHora = dto.dataHora();
        this.dataCriacao = dto.dataCriacao();
    }

}
