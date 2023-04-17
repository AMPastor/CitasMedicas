package com.metaenlace.citasmedicas.mapper;

import com.metaenlace.citasmedicas.entity.Cita;
import com.metaenlace.citasmedicas.entitydto.CitaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    CitaMapper INSTANCE = Mappers.getMapper(CitaMapper.class);

    CitaDTO citaToCitaDTO(Cita cita);

    Cita citaDTOToCita(CitaDTO citaDTO);

}
