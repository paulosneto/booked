package com.agenda.book.dtos;

import com.agenda.book.domains.Paciente;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.NotFound;

import java.time.LocalDateTime;

public record AgendaDTO(@NotBlank String descricao,
                        @NotBlank LocalDateTime dataHoraAgendamento,
                        //@NotBlank LocalDateTime dataCriacao,
                        @NotBlank String cpfPaciente) {
}
