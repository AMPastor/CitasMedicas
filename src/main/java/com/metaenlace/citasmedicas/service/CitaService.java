package com.metaenlace.citasmedicas.service;

import com.metaenlace.citasmedicas.entitydto.CitaDTO;
import com.metaenlace.citasmedicas.entitydto.MedicoDTO;
import com.metaenlace.citasmedicas.entitydto.PacienteDTO;
import com.metaenlace.citasmedicas.entity.Cita;
import com.metaenlace.citasmedicas.entity.Medico;
import com.metaenlace.citasmedicas.entity.Paciente;
import com.metaenlace.citasmedicas.mapper.CitaMapper;
import com.metaenlace.citasmedicas.mapper.MedicoMapper;
import com.metaenlace.citasmedicas.mapper.PacienteMapper;
import com.metaenlace.citasmedicas.repository.CitaRepository;
import com.metaenlace.citasmedicas.repository.MedicoRepository;
import com.metaenlace.citasmedicas.repository.PacienteRepository;
import com.metaenlace.citasmedicas.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Collections;
import java.util.List;

@Service
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private CitaMapper citaMapper;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private MedicoRepository medicoRepository;

    public Cita insertar(CitaDTO citaDTO){

        Paciente paciente = pacienteRepository.findById(citaDTO.getPacienteId()).orElse(null);
        if(paciente == null){
            throw new NotFoundException("Paciente no encontrado");
        }
        Medico medico = medicoRepository.findById(citaDTO.getMedicoId()).orElse(null);
        if(medico == null){
            throw new NotFoundException("Médico no encontrado");
        }

        Cita cita = citaMapper.citaDTOToCita(citaDTO);
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        return citaRepository.save(cita);
    }

    public Cita editarCita(Long id, CitaDTO citaDTO){
        Optional<Paciente> pacienteOpt = pacienteRepository.findById(citaDTO.getPacienteId());
        if(pacienteOpt.isEmpty()){
            throw new NotFoundException("Paciente no encontrado");
        }
        Optional<Medico> medicoOpt = medicoRepository.findById(citaDTO.getMedicoId());
        if(medicoOpt.isEmpty()){
            throw new NotFoundException("Médico no encontrado");
        }
        Cita cita = citaMapper.citaDTOToCita(citaDTO);
        //cita.setPaciente(pacienteOpt.get());
        //cita.setMedico(medicoOpt.get());
        cita.setId(id);
        return citaRepository.save(cita);
    }

    public List<CitaDTO> listaCitas(){
        List<CitaDTO> lista = new ArrayList<>();
        Iterable<Cita> listaCitas = citaRepository.findAll();
        for(Cita cita : listaCitas){
            CitaDTO citaDTO = citaMapper.citaToCitaDTO(cita);
            lista.add(citaDTO);
        }
        return lista;
    }

    public CitaDTO buscarCita(long id){
        Cita cita = citaRepository.findById(id).orElse(null);
        if(cita == null){
            throw new NotFoundException("Cita no existe");
        }
        return citaMapper.citaToCitaDTO(cita);
    }

    public void eliminarCita(long id){
        Optional<Cita> cita = citaRepository.findById(id);
        if(cita.isEmpty()){
            throw new NotFoundException("Cita no encontrada");
        }else {
            citaRepository.deleteById(id);
        }
    }
}
