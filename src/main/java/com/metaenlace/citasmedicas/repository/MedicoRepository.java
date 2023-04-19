package com.metaenlace.citasmedicas.repository;

import com.metaenlace.citasmedicas.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    //public Optional<Medico> findByUsuario(String usuario);
}


