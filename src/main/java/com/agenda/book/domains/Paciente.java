package com.agenda.book.domains;

import com.agenda.book.dtos.PacienteDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name= "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idPaciente;
    @Column(name = "nomePaciente")
    public String nomePaciente;
    @Column(name = "cpfPaciente")
    public String cpfPaciente;
    @Column(name = "emailPaciente")
    public String emailPaciente;
    @Column(name = "telefonePaciente")
    public String telefonePaciente;

    public Paciente(PacienteDTO dto){
        this.nomePaciente = dto.nomePaciente();
        this.cpfPaciente = dto.cpfPaciente();
        this.emailPaciente = dto.emailPaciente();
        this.telefonePaciente = dto.telefonePaciente();
    }

}