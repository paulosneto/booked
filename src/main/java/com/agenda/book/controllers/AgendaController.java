package com.agenda.book.controllers;

import com.agenda.book.domains.Agenda;
import com.agenda.book.dtos.AgendaDTO;
import com.agenda.book.repositories.AgendaRepository;
import com.agenda.book.services.AgendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService service;

    @Operation(summary = "Created new Schedule(Agendamento)")
    @ApiResponse(responseCode = "201", description = "Schedule created successfully")
    @PostMapping
    public ResponseEntity<Agenda> createAgenda(@RequestBody AgendaDTO dto){

        Agenda agenda = this.service.save(dto);

        return ResponseEntity.ok(agenda);
    }

    @Operation(summary = "Get all Schedules(Agendamentos)", description = "Returned all Schedules")
    @ApiResponse(responseCode = "200", description = "Schedules found")
    @GetMapping
    public ResponseEntity<List<Agenda>> getAll(){
        return ResponseEntity.ok(this.service.findAll());
    }

    @Operation(summary = "Get schedule(Agendamento) by creation date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schedule found"),
            @ApiResponse(responseCode = "404", description = "Schedule not found")
    })
    @GetMapping("/{dataCriacao}")
    public ResponseEntity<List<Agenda>> findByDataCriacao(@PathVariable("dataCriacao") LocalDateTime dataCriacao, String cpfPaciente){
       List<Agenda> lt = this.service.findByDataCriacao(dataCriacao);

       return ResponseEntity.ok().body(lt);
    }





}
