package com.agenda.book.repositories;

import com.agenda.book.domains.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgendaRepository  extends JpaRepository<Agenda, Long> {

    Optional<List<Agenda>> findByDataCriacao(LocalDateTime dataCriacao);
}
