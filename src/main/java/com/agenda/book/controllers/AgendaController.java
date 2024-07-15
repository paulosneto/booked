package com.agenda.book.controllers;

import com.agenda.book.domains.Agenda;
import com.agenda.book.dtos.AgendaDTO;
import com.agenda.book.repositories.AgendaRepository;
import com.agenda.book.services.AgendaService;
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

    @PostMapping
    public ResponseEntity<Agenda> createAgenda(@RequestBody AgendaDTO dto){

        Agenda agenda = this.service.save(dto);
        return ResponseEntity.ok(agenda);
    }

    @GetMapping
    public ResponseEntity<List<Agenda>> getAll(){
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/{dataCriacao}")
    public ResponseEntity<List<Agenda>> findByDataCriacao(@PathVariable("dataCriacao") LocalDateTime dataCriacao){
       List<Agenda> lt = this.service.findByDataCriacao(dataCriacao);

       return ResponseEntity.ok().body(lt);
    }



}
