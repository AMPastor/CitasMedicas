package com.metaenlace.citasmedicas.mapper;

import com.metaenlace.citasmedicas.entity.Usuario;
import com.metaenlace.citasmedicas.entitydto.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);
}


