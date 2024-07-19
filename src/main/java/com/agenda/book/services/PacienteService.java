package com.agenda.book.services;

import com.agenda.book.domains.Paciente;
import com.agenda.book.dtos.PacienteDTO;
import com.agenda.book.exceptions.ExceptionCustomError;
import com.agenda.book.exceptions.ExceptionHandlerCustom;
import com.agenda.book.exceptions.ProblemDetailsClass;
import com.agenda.book.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PacienteService {

    // Inicia a chamada da interface repository
    @Autowired
    private PacienteRepository pacienteRepository;


    // Salva o objeto Paciente
    public Paciente save(PacienteDTO dto) {
        // Inicia o objeto paciente
        Paciente p = new Paciente(dto);

        // Faz uma busca no banco para saber se o usuario existe
       var buscaId = this.pacienteRepository.findByCpfPaciente(p.getCpfPaciente());
       //if(buscaId != null && buscaId.getCpfPaciente().equals(dto.cpfPaciente())){
       if(buscaId.isPresent()){
           // Se o paciente existir retorna erro de procacemento
           //throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
           throw new ExceptionCustomError("Já existe um cadastro com esse CPF", 422);
       }else{
            // Caso nao exista faz o insert no banco
            return this.pacienteRepository.save(p);
       }
    }


    // Lista todos os pacientes
    public List<Paciente> listPaciente(){

        LocalDateTime lt = LocalDateTime.now();

        return this.pacienteRepository.findAll();
    }

    // Faz uma busca no banco para encontrar o Paciente pelo id informado
    //public Optional<Paciente> findById(Long id) throws  Exception {
    public Paciente findById(Long id) {
        var p = this.pacienteRepository.findById(id).orElseThrow(()-> new ExceptionCustomError("Paciente não encontrado", 404));
        /*if(!p.isPresent()){
            throw new Exception("O paciente informado não existe.");
        }*/
        return p;
    }


    // Faz busca ao paciente por CPF
    public Optional<Paciente> findByCpfPaciente(String cpf){
        //var paciente = this.pacienteRepository.findByCpfPaciente(cpf).orElseThrow(() -> new ExceptionCustomError("Paciente não encontrado", 404));
        var paciente = this.pacienteRepository.findByCpfPaciente(cpf);

        if(!paciente.isPresent()){
            //throw new ExceptionCustomError("Paciente não encontrado", 404);
            throw new ProblemDetailsClass("paciente nao encontrado", 403);
        }
        //var paciente1 = this.pacienteRepository.findByCpfPaciente(cpf).orElseThrow(() -> new ProblemDetailsClass());
        return paciente;
    }



    // Faz o update dos dados do paciente pelo id informado
    public Paciente update(PacienteDTO dto) {


        //Pesquisa no banco para verificar se o paciente existe
        var p = this.pacienteRepository.findByCpfPaciente(dto.cpfPaciente());

        Paciente paciente = null;

        // Se o paciente existir, faz a atualização dos dados
        //if(p.getCpfPaciente().equals(dto.cpfPaciente())){
        if(p.isPresent()){
             paciente = new Paciente(dto);
            this.pacienteRepository.save(paciente);
        }else{
            throw new ExceptionCustomError("O CPF informado não está cadastrado.", 404);
        }
        return paciente;
    }


    // Faz deleção do Paciente pelo id informado
    public void deletePaciente(Long id) {
        var p = this.pacienteRepository.findById(id).orElseThrow(() -> new ExceptionCustomError("Paciente informado não encontrado",404));
        this.pacienteRepository.delete(p);
    }

    // Faz deleção pelo CPF do paciente
    public void deletePacientePorCpf(String cpf){
        var paciente = this.pacienteRepository.findByCpfPaciente(cpf).orElseThrow(() -> new ExceptionCustomError("Não foi encontrado nenhum paciente com o CPF informado", 404));
    }

}
