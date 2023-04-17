package com.metaenlace.citasmedicas.mapper;

import com.metaenlace.citasmedicas.entity.Medico;
import com.metaenlace.citasmedicas.entitydto.MedicoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MedicoMapper {
    MedicoMapper INSTANCE = Mappers.getMapper(MedicoMapper.class);
    Medico medicoDTOToMedico(MedicoDTO medicoDTO);
    MedicoDTO medicoToMedicoDTO(Medico medico);
}


