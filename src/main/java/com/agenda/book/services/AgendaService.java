package com.agenda.book.services;

import com.agenda.book.domains.Agenda;
import com.agenda.book.dtos.AgendaDTO;
import com.agenda.book.exceptions.ExceptionCustomError;
import com.agenda.book.repositories.AgendaRepository;
import com.agenda.book.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import java.time.LocalDateTime;
import java.util.List;


@Transactional
@Service
public class AgendaService {

    @Autowired
    private AgendaRepository repository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public Agenda save(AgendaDTO dto){

        Agenda agenda;
        //var agendaBanco = this.repository.findById(dto.paciente().getIdPaciente()).orElseThrow(()-> new ExceptionCustomError("Já existe um agendamento para este paciente.",422));
        var paciente = this.pacienteRepository.findByCpfPaciente(dto.cpfPaciente());



        //if(paciente != null && paciente.getCpfPaciente().equals(dto.cpfPaciente())){
        if(paciente.isPresent()){
             agenda = new Agenda(dto);
             agenda.setDataCriacao(LocalDateTime.now());

             agenda.setPaciente(paciente.get());
          return this.repository.save(agenda);

        }else{
            throw new ExceptionCustomError("Não foi encontrado paciente com o CPF informado", 404);
        }
    }

    public List<Agenda> findAll(){
        return this.repository.findAll();
    }

    public List<Agenda> findByDataCriacao( LocalDateTime dataCriacao){
        var buscaPorData = this.repository.findByDataCriacao(dataCriacao).orElseThrow(()-> new ExceptionCustomError("Nenhum agendamento foi encontrado para data informada.", 404));
        return buscaPorData;
    }

    /*public List<Agenda> findByCpfPaciente(String cpfPaciente){
        var agendamentosPorCpf = this.repository.findByCpfPaciente(cpfPaciente).orElseThrow(() -> new ExceptionCustomError("Não foram encontrado agendamento para este CPF informado.", 404));

        return agendamentosPorCpf;
    }*/

   /* public void delete(Long id){
        Agenda agenda = this.repository.findById(id).orElseThrow(() -> new ExceptionCustomError("Paciente não encontrado", 404));
    }*/

}
