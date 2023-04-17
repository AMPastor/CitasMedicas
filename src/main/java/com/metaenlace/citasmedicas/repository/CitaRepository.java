
package com.metaenlace.citasmedicas.repository;

import com.metaenlace.citasmedicas.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

}
