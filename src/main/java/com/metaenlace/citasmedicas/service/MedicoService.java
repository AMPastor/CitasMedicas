
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

}
