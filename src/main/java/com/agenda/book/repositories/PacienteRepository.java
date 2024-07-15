package com.agenda.book.repositories;

import com.agenda.book.domains.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByCpfPaciente(String cpf);
    //Paciente findByCpfPacienteEmailPaciente();
}
