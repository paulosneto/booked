package com.agenda.book.domains;

import com.agenda.book.dtos.PacienteDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Schema(description = "Paciente model")
@Table(name= "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;
    @Column(name = "nomePaciente")
    private String nomePaciente;
    @Schema(description = "Single CPF")
    @Column(name = "cpfPaciente", unique = true)
    private String cpfPaciente;
    @Column(name = "emailPaciente")
    private String emailPaciente;
    @Column(name = "telefonePaciente")
    private String telefonePaciente;

    public Paciente(PacienteDTO dto){
        this.nomePaciente = dto.nomePaciente();
        this.cpfPaciente = dto.cpfPaciente();
        this.emailPaciente = dto.emailPaciente();
        this.telefonePaciente = dto.telefonePaciente();
    }

    public Paciente(Long idPaciente, String nomePaciente, String cpfPaciente, String emailPaciente, String telefonePaciente){
        this.idPaciente = idPaciente;
        this.nomePaciente = nomePaciente;
        this.cpfPaciente = cpfPaciente;
        this.emailPaciente = emailPaciente;
        this.telefonePaciente = telefonePaciente;
    }
    public Paciente get(){
        return new Paciente(idPaciente, nomePaciente, cpfPaciente, emailPaciente, telefonePaciente);
    }


}
