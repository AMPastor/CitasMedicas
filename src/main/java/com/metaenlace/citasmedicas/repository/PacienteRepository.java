package com.metaenlace.citasmedicas.repository;

import com.metaenlace.citasmedicas.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    //public Optional<Paciente> findByUsuario(String usuario);
}


