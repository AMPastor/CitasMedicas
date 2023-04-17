package com.metaenlace.citasmedicas.mapper;

import com.metaenlace.citasmedicas.entitydto.MedicoDTO;
import com.metaenlace.citasmedicas.entity.Medico;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface MedicoMapper {
    MedicoMapper INSTANCE = Mappers.getMapper(MedicoMapper.class);
    Medico medicoDTOToMedico(MedicoDTO medicoDTO);
    MedicoDTO medicoToMedicoDTO(Medico medico);
}


