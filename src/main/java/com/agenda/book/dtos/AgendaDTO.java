package com.agenda.book.dtos;

import com.agenda.book.domains.Paciente;

import java.time.LocalDateTime;

public record AgendaDTO(String descricao, LocalDateTime dataHora, LocalDateTime dataCriacao, Paciente paciente) {
}
