package com.agenda.book.services;

import com.agenda.book.domains.Agenda;
import com.agenda.book.dtos.AgendaDTO;
import com.agenda.book.exceptions.ExceptionCustomError;
import com.agenda.book.repositories.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Transactional
@Service
public class AgendaService {

    @Autowired
    private AgendaRepository repository;

    public AgendaService(AgendaRepository repository){
        this.repository = repository;
    }

    public Agenda save(AgendaDTO dto){

        var agendaBanco = this.repository.findById(dto.paciente().getIdPaciente()).orElseThrow(()-> new ExceptionCustomError("Já existe um agendamento para este paciente.",422));

        Agenda agenda = new Agenda(dto);

      return this.repository.save(agenda);
    }

    public List<Agenda> findAll(){
        return this.repository.findAll();
    }

    public List<Agenda> findByDataCriacao( LocalDateTime dataCriacao){
        var buscaPorData = this.repository.findByDataCriacao(dataCriacao).orElseThrow(()-> new ExceptionCustomError("Nenhum agendamento foi encontrado para data informada.", 404));
        return buscaPorData;
    }


   /* public void delete(Long id){
        Agenda agenda = this.repository.findById(id).orElseThrow(() -> new ExceptionCustomError("Paciente não encontrado", 404));
    }*/

}
