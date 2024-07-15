package com.agenda.book.controllers;

import com.agenda.book.domains.Paciente;
import com.agenda.book.dtos.PacienteDTO;
import com.agenda.book.services.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody PacienteDTO dto){
        //Passa a DTO de requisição para a classe service de
        // paciente, onde fará a configuração do objeto e salvará no banco
        Paciente p = this.service.save(dto);
        // Retnora OK para o usuario caso tudo venha a dar certo
        return ResponseEntity.ok().body(p);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listPacientes(){
        // Retorna a lista com todos os pacientes
        return ResponseEntity.ok().body(this.service.listPaciente());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> findByIdPaciente(@PathVariable("id") Long id) {
       var paciente = this.service.findById(id);

       return ResponseEntity.ok().body(paciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Paciente> delete(@PathVariable("id") Long id){
        this.service.deletePaciente(id);
        return ResponseEntity.noContent().build();


    }
}
