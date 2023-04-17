package com.metaenlace.citasmedicas.repository;

import com.metaenlace.citasmedicas.entity.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
}


