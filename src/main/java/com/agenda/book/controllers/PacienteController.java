package com.agenda.book.controllers;

import com.agenda.book.domains.Paciente;
import com.agenda.book.dtos.PacienteDTO;
import com.agenda.book.services.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paciente")
public class PacienteController {

    //Instancia da classe Service
    @Autowired
    private PacienteService service;

    public PacienteController(PacienteService service){
        this.service = service;
    }

    @Operation(summary = "Created new Patient(Paciente)")
    @ApiResponse(responseCode = "201", description = "Patient saved successfully")
    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody PacienteDTO dto){
        //Passa a DTO de requisição para a classe service de
        // paciente, onde fará a configuração do objeto e salvará no banco
        Paciente p = this.service.save(dto);
        // Retnora OK para o usuario caso tudo venha a dar certo
        return ResponseEntity.ok().body(p);
    }

    @Operation(summary = "Get all Patients(Pacientes)")
    @ApiResponse(responseCode = "200", description = "Patients found")
    @GetMapping
    public ResponseEntity<List<Paciente>> listPacientes(){
        // Retorna a lista com todos os pacientes
        return ResponseEntity.ok().body(this.service.listPaciente());
    }

    @Operation(summary = "Find Patient(Paciente) by CPF")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "201", description = "Patient found"),
                @ApiResponse(responseCode = "404", description = "Patient not found")
            })
    @GetMapping("/{cpf}")
    public ResponseEntity<Paciente> findByIdPaciente(@PathVariable("cpf") String cpf) {
       //var paciente = this.service.findById(id);
        var paciente = this.service.findByCpfPaciente(cpf);

       return ResponseEntity.ok().body(paciente);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Remove Patient(Paciente) by CPF"),
        @ApiResponse(responseCode = "404", description = "CPF the Patient(Paciente) not found")
    })
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Paciente> delete(@PathVariable("cpf") String cpf){
        //this.service.deletePaciente(id);
        this.service.deletePacientePorCpf(cpf);
        return ResponseEntity.noContent().build();


    }
}
