package com.metaenlace.citasmedicas.mapper;

import com.metaenlace.citasmedicas.entity.Paciente;
import com.metaenlace.citasmedicas.entitydto.PacienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);
    PacienteDTO pacienteToPacienteDTO(Paciente paciente);
    Paciente pacienteDTOToPaciente(PacienteDTO pacienteDTO);

}
