package com.metaenlace.citasmedicas.service;

import com.metaenlace.citasmedicas.entity.Paciente;
import com.metaenlace.citasmedicas.entitydto.PacienteDTO;
import com.metaenlace.citasmedicas.exceptions.NotFoundException;
import com.metaenlace.citasmedicas.mapper.PacienteMapper;
import com.metaenlace.citasmedicas.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PacienteMapper pacienteMapper;

    public List<PacienteDTO> findAll() {
        List<PacienteDTO> pacientesDTO = new ArrayList<>();
        pacienteRepository.findAll().forEach(paciente -> pacientesDTO.add(pacienteMapper.pacienteToPacienteDTO(paciente)));
        return pacientesDTO;
    }

    public Optional<PacienteDTO> findById(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            return Optional.of(pacienteMapper.pacienteToPacienteDTO(paciente.get()));
        } else {
            throw new NotFoundException("Paciente no encontrado");
        }
    }

    public PacienteDTO save(PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteMapper.pacienteDTOToPaciente(pacienteDTO);
        return pacienteMapper.pacienteToPacienteDTO(pacienteRepository.save(paciente));
    }

    public PacienteDTO update(Long id, PacienteDTO pacienteDTO) {
        Optional<Paciente> pacienteToUpdate = pacienteRepository.findById(id);
        if (pacienteToUpdate.isPresent()) {
            Paciente paciente = pacienteToUpdate.get();
            paciente.setNombre(pacienteDTO.getNombre());
            paciente.setApellidos(pacienteDTO.getApellidos());
            paciente.setUsuario(pacienteDTO.getUsuario());
            paciente.setClave(pacienteDTO.getClave());
            paciente.setNSS(pacienteDTO.getNss());
            paciente.setNumTarjeta(pacienteDTO.getNumTarjeta());
            paciente.setTelefono(pacienteDTO.getTelefono());
            paciente.setDireccion(pacienteDTO.getDireccion());
            return pacienteMapper.pacienteToPacienteDTO(pacienteRepository.save(paciente));
        } else {
            throw new NotFoundException("Paciente no encontrado");
        }
    }


    public void deleteById(Long id) {
        pacienteRepository.deleteById(id);
    }

}*/

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<PacienteDTO> findAll() {
        List<PacienteDTO> pacientesDTO = new ArrayList<>();
        pacienteRepository.findAll().forEach(paciente -> pacientesDTO.add(PacienteMapper.INSTANCE.pacienteToPacienteDTO(paciente)));
        return pacientesDTO;
    }

    public Optional<PacienteDTO> findById(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            return Optional.of(PacienteMapper.INSTANCE.pacienteToPacienteDTO(paciente.get()));
        } else {
            throw new NotFoundException("Paciente no encontrado");
        }
    }

    public PacienteDTO save(PacienteDTO pacienteDTO) {
        Paciente paciente = PacienteMapper.INSTANCE.pacienteDTOToPaciente(pacienteDTO);
        return PacienteMapper.INSTANCE.pacienteToPacienteDTO(pacienteRepository.save(paciente));
    }

    public PacienteDTO update(Long id, PacienteDTO pacienteDTO) {
        Optional<Paciente> pacienteToUpdate = pacienteRepository.findById(id);
        if (pacienteToUpdate.isPresent()) {
            Paciente paciente = pacienteToUpdate.get();
            paciente.setNombre(pacienteDTO.getNombre());
            paciente.setApellidos(pacienteDTO.getApellidos());
            paciente.setUsuario(pacienteDTO.getUsuario());
            paciente.setClave(pacienteDTO.getClave());
            paciente.setNSS(pacienteDTO.getNss());
            paciente.setNumTarjeta(pacienteDTO.getNumTarjeta());
            paciente.setTelefono(pacienteDTO.getTelefono());
            paciente.setDireccion(pacienteDTO.getDireccion());
            return PacienteMapper.INSTANCE.pacienteToPacienteDTO(pacienteRepository.save(paciente));
        } else {
            throw new NotFoundException("Paciente no encontrado");
        }
    }

    public void deleteById(Long id) {
        pacienteRepository.deleteById(id);
    }

}


