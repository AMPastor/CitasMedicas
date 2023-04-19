package com.metaenlace.citasmedicas.service;

import com.metaenlace.citasmedicas.entitydto.CitaDTO;
import com.metaenlace.citasmedicas.entity.Cita;
import com.metaenlace.citasmedicas.entity.Medico;
import com.metaenlace.citasmedicas.entity.Paciente;
import com.metaenlace.citasmedicas.repository.CitaRepository;
import com.metaenlace.citasmedicas.repository.MedicoRepository;
import com.metaenlace.citasmedicas.repository.PacienteRepository;
import com.metaenlace.citasmedicas.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
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

    //Mostrar Citas
    //El método findAll devuelve una lista de objetos CitaDTO (List<CitaDTO>).
    public List<CitaDTO> findAll(){
        List<CitaDTO> lista = new ArrayList<>();
        Iterable<Cita> listaCitas = citaRepository.findAll();
        for(Cita cita : listaCitas){
            CitaDTO citaDTO = INSTANCE.citaToCitaDTO(cita);
            lista.add(citaDTO);
        }
        return lista;
    }

    //Mostrar Citas por ID
    //El método findById devuelve un objeto CitaDTO (CitaDTO).
    public CitaDTO findById(long id){
        Cita cita = citaRepository.findById(id).orElse(null);
        if(cita == null){
            throw new NotFoundException("No existe ninguna cita.");
        }
        return INSTANCE.citaToCitaDTO(cita);
    }


    //Guardar citas
    //El método save devuelve un objeto CitaDTO (CitaDTO).
    public CitaDTO save(CitaDTO citaDTO) {
        Paciente paciente = pacienteRepository.findById(citaDTO.getPacienteId()).orElse(null);
        if (paciente == null) {
            throw new NotFoundException("No se ha encontrado ningún paciente.");
        }
        Medico medico = medicoRepository.findById(citaDTO.getMedicoId()).orElse(null);
        if (medico == null) {
            throw new NotFoundException("No se ha encontrado ningún paciente médico.");
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

    //Actualizar citas
    //El método update devuelve un objeto Cita (Cita).
    public Cita update(Long id, CitaDTO citaDTO){
        Optional<Paciente> pacienteOpt = pacienteRepository.findById(citaDTO.getPacienteId());
        if(pacienteOpt.isEmpty()){
            throw new NotFoundException("No se ha encontrado ningún paciente.");
        }
        Optional<Medico> medicoOpt = medicoRepository.findById(citaDTO.getMedicoId());
        if(medicoOpt.isEmpty()){
            throw new NotFoundException("No se ha encontrado ningún médico.");
        }
        Cita cita = INSTANCE.citaDTOToCita(citaDTO);
        cita.setId(id);
        return citaRepository.save(cita);
    }

    //Borrar citas por id
    //El método deleteById no devuelve nada (void).
    //El tipo de retorno en este método se omite y se utiliza la palabra clave void para indicar que el método no devuelve ningún valor.
    public void deleteById(long id){
        Optional<Cita> cita = citaRepository.findById(id);
        if(cita.isEmpty()){
            throw new NotFoundException("No se ha encontrado ninguna cita.");
        } else {
            citaRepository.deleteById(id);
        }
    }
}
