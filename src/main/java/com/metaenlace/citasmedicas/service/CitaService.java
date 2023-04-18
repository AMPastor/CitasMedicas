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

import static com.metaenlace.citasmedicas.mapper.CitaMapper.INSTANCE;

@Service
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private MedicoRepository medicoRepository;

    /*public Cita insertar(CitaDTO citaDTO){

        Paciente paciente = pacienteRepository.findById(citaDTO.getPacienteId()).orElse(null);
        if(paciente == null){
            throw new NotFoundException("Paciente no encontrado");
        }
        Medico medico = medicoRepository.findById(citaDTO.getMedicoId()).orElse(null);
        if(medico == null){
            throw new NotFoundException("Médico no encontrado");
        }

        Cita cita = INSTANCE.citaDTOToCita(citaDTO);
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        return citaRepository.save(cita);
    }

     */

    public CitaDTO create(CitaDTO citaDTO) {
        Paciente paciente = pacienteRepository.findById(citaDTO.getPacienteId()).orElse(null);
        if (paciente == null) {
            throw new NotFoundException("Paciente no encontrado");
        }
        Medico medico = medicoRepository.findById(citaDTO.getMedicoId()).orElse(null);
        if (medico == null) {
            throw new NotFoundException("Médico no encontrado");
        }
        Cita cita = new Cita();
        // Asigna los valores de los atributos de la entidad cita
        // a partir de los valores del DTO citaDTO
        cita.setFechaHora(citaDTO.getFechaHora());
        cita.setMotivoCita(citaDTO.getMotivoCita());
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        // Guarda la entidad en la base de datos
        citaRepository.save(cita);
        // Crea un DTO a partir de la entidad guardada
        CitaDTO savedCitaDTO = INSTANCE.citaToCitaDTO(cita);
        // Devuelve el DTO creado
        return savedCitaDTO;
    }

    public Cita update(Long id, CitaDTO citaDTO){
        Optional<Paciente> pacienteOpt = pacienteRepository.findById(citaDTO.getPacienteId());
        if(pacienteOpt.isEmpty()){
            throw new NotFoundException("Paciente no encontrado");
        }
        Optional<Medico> medicoOpt = medicoRepository.findById(citaDTO.getMedicoId());
        if(medicoOpt.isEmpty()){
            throw new NotFoundException("Médico no encontrado");
        }
        Cita cita = INSTANCE.citaDTOToCita(citaDTO);
        cita.setId(id);
        return citaRepository.save(cita);
    }

    public List<CitaDTO> findAll(){
        List<CitaDTO> lista = new ArrayList<>();
        Iterable<Cita> listaCitas = citaRepository.findAll();
        for(Cita cita : listaCitas){
            CitaDTO citaDTO = INSTANCE.citaToCitaDTO(cita);
            lista.add(citaDTO);
        }
        return lista;
    }

    public CitaDTO findById(long id){
        Cita cita = citaRepository.findById(id).orElse(null);
        if(cita == null){
            throw new NotFoundException("Cita no existe");
        }
        return INSTANCE.citaToCitaDTO(cita);
    }

    public void deleteById(long id){
        Optional<Cita> cita = citaRepository.findById(id);
        if(cita.isEmpty()){
            throw new NotFoundException("Cita no encontrada");
        } else {
            citaRepository.deleteById(id);
        }
    }
}
