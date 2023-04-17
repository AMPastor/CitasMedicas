
package com.metaenlace.citasmedicas.service;

import com.metaenlace.citasmedicas.entity.Medico;
import com.metaenlace.citasmedicas.entitydto.MedicoDTO;
import com.metaenlace.citasmedicas.exceptions.NotFoundException;
import com.metaenlace.citasmedicas.mapper.MedicoMapper;
import com.metaenlace.citasmedicas.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/* No estaba usando INSTANCE
@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private MedicoMapper medicoMapper;

    public List<MedicoDTO> findAll() {
        List<MedicoDTO> medicosDTO = new ArrayList<>();
        medicoRepository.findAll().forEach(medico -> medicosDTO.add(medicoMapper.medicoToMedicoDTO(medico)));
        return medicosDTO;
    }

    public Optional<MedicoDTO> findById(Long id) {
        Optional<Medico> medico = medicoRepository.findById(id);
        if (medico.isPresent()) {
            return Optional.of(medicoMapper.medicoToMedicoDTO(medico.get()));
        } else {
            throw new NotFoundException("Medico no encontrado");
        }
    }

    public MedicoDTO save(MedicoDTO medicoDTO) {
        Medico medico = medicoMapper.medicoDTOToMedico(medicoDTO);
        return medicoMapper.medicoToMedicoDTO(medicoRepository.save(medico));
    }

    public MedicoDTO update(Long id, MedicoDTO medicoDTO) {
        Optional<Medico> medicoToUpdate = medicoRepository.findById(id);
        if (medicoToUpdate.isPresent()) {
            Medico medico = medicoToUpdate.get();
            medico.setNombre(medicoDTO.getNombre());
            medico.setApellidos(medicoDTO.getApellidos());
            medico.setUsuario(medicoDTO.getUsuario());
            medico.setClave(medicoDTO.getClave());
            medico.setNumColegiado(medicoDTO.getNumColegiado());
            return medicoMapper.medicoToMedicoDTO(medicoRepository.save(medico));
        } else {
            throw new NotFoundException("Medico no encontrado");
        }
    }

    public void deleteById(Long id) {
        medicoRepository.deleteById(id);
    }

}*/

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    //Recupera lista objetos Medico de la base de datos y devuelve una lista de objetos MedicoDTO
    public List<MedicoDTO> findAll() {
        List<MedicoDTO> medicosDTO = new ArrayList<>();
        medicoRepository.findAll().forEach(medico -> medicosDTO.add(MedicoMapper.INSTANCE.medicoToMedicoDTO(medico)));
        return medicosDTO;
    }

    //Recupera el objeto Medico de la base de datos con el identificador ID
    public Optional<MedicoDTO> findById(Long id) {
        Optional<Medico> medico = medicoRepository.findById(id);
        if (medico.isPresent()) {
            return Optional.of(MedicoMapper.INSTANCE.medicoToMedicoDTO(medico.get()));
        } else {
            throw new NotFoundException("Medico no encontrado");
        }
    }

    //Convierte objeto MedicoDTO en Medico.
    // Guarda base de datos, lo convierte en medicoDTO y lo devuelve.
    public MedicoDTO save(MedicoDTO medicoDTO) {
        Medico medico = MedicoMapper.INSTANCE.medicoDTOToMedico(medicoDTO);
        return MedicoMapper.INSTANCE.medicoToMedicoDTO(medicoRepository.save(medico));
    }

    /*Recupera el objeto Medico de la base de datos con el identificador id.
    Si el objeto Medico existe, actualiza sus propiedades con los valores del objeto MedicoDTO.
    Guarda el objeto Medico actualizado en la base de datos.
    Convierte el objeto Medico actualizado en un objeto MedicoDTO y lo devuelve.
    Si el objeto Medico no existe, lanza una excepción NotFoundException.*/
    public MedicoDTO update(Long id, MedicoDTO medicoDTO) {
        Optional<Medico> medicoToUpdate = medicoRepository.findById(id);
        if (medicoToUpdate.isPresent()) {
            Medico medico = medicoToUpdate.get();
            medico.setNombre(medicoDTO.getNombre());
            medico.setApellidos(medicoDTO.getApellidos());
            medico.setUsuario(medicoDTO.getUsuario());
            medico.setClave(medicoDTO.getClave());
            medico.setNumColegiado(medicoDTO.getNumColegiado());
            return MedicoMapper.INSTANCE.medicoToMedicoDTO(medicoRepository.save(medico));
        } else {
            throw new NotFoundException("Medico no encontrado");
        }
    }

    //Elimina el objeto Medico de la base de datos con el identificador id.
    public void deleteById(Long id) {
        medicoRepository.deleteById(id);
    }

}

