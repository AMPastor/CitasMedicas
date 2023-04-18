package com.metaenlace.citasmedicas.security;

import com.metaenlace.citasmedicas.entity.Usuario;
import com.metaenlace.citasmedicas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository
                .findOneByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("El " + username + " no existe."));

        return new UserDetailsImpl(usuario);

    }
}
