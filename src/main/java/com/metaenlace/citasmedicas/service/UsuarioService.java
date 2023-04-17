package com.metaenlace.citasmedicas.service;

import com.metaenlace.citasmedicas.entity.Usuario;
import com.metaenlace.citasmedicas.entitydto.UsuarioDTO;
import com.metaenlace.citasmedicas.repository.UsuarioRepository;
import com.metaenlace.citasmedicas.mapper.UsuarioMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
   @Autowired
   private UsuarioRepository usuarioRepository;
   public Optional<UsuarioDTO> findByUsuario(String usuario) {
       Optional<Usuario> usuarioEntity = usuarioRepository.findByUsuario(usuario);
       if (usuarioEntity.isEmpty()) {
           return Optional.empty();
       }

       return Optional.of(UsuarioMapper.INSTANCE.usuarioToUsuarioDTO(usuarioEntity.get()));
   }
}


