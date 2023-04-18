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

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    //El método findAll devuelve una lista de objetos PacienteDTO
    // obtenidos a partir de todos los pacientes en la base de datos.
    public List<PacienteDTO> findAll() {
        List<PacienteDTO> pacientesDTO = new ArrayList<>();
        pacienteRepository.findAll().forEach(paciente -> pacientesDTO.add(PacienteMapper.INSTANCE.pacienteToPacienteDTO(paciente)));
        return pacientesDTO;
    }

    //El método findById devuelve un objeto Optional<PacienteDTO> que representa al
    // paciente con el ID proporcionado o lanza una excepción NotFoundException.
    public Optional<PacienteDTO> findById(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            return Optional.of(PacienteMapper.INSTANCE.pacienteToPacienteDTO(paciente.get()));
        } else {
            throw new NotFoundException("Paciente no encontrado");
        }
    }

    //El método save guarda un nuevo paciente en la base de datos y lo devuelve convertido
    // en un objeto PacienteDTO
    public PacienteDTO save(PacienteDTO pacienteDTO) {
        Paciente paciente = PacienteMapper.INSTANCE.pacienteDTOToPaciente(pacienteDTO);
        return PacienteMapper.INSTANCE.pacienteToPacienteDTO(pacienteRepository.save(paciente));
    }

    //El método update actualiza un paciente existente en la base de datos con los valores proporcionados
    //y devuelve el paciente actualizado convertido en un objeto PacienteDTO.
    public PacienteDTO update(Long id, PacienteDTO pacienteDTO) {
        Optional<Paciente> pacienteToUpdate = pacienteRepository.findById(id);
        if (pacienteToUpdate.isPresent()) {
            Paciente paciente = pacienteToUpdate.get();
            paciente.setNombre(pacienteDTO.getNombre());
            paciente.setApellidos(pacienteDTO.getApellidos());
            paciente.setUsuario(pacienteDTO.getUsuario());
            paciente.setClave(pacienteDTO.getClave());
            paciente.setNss(pacienteDTO.getNss());
            paciente.setNumTarjeta(pacienteDTO.getNumTarjeta());
            paciente.setTelefono(pacienteDTO.getTelefono());
            paciente.setDireccion(pacienteDTO.getDireccion());
            return PacienteMapper.INSTANCE.pacienteToPacienteDTO(pacienteRepository.save(paciente));
        } else {
            throw new NotFoundException("Paciente no encontrado");
        }
    }

    //El método deleteById elimina un paciente de la base de datos utilizando el ID proporcionado.
    public void deleteById(Long id) {
        pacienteRepository.deleteById(id);
    }

}


