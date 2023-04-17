package com.metaenlace.citasmedicas.controller;

import com.metaenlace.citasmedicas.entitydto.PacienteDTO;
import com.metaenlace.citasmedicas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    // Mostrar listado pacientes
    @GetMapping("/lista")
    public ResponseEntity<List<PacienteDTO>> findAll() {
        List<PacienteDTO> pacientesDTO = pacienteService.findAll();
        return new ResponseEntity<>(pacientesDTO, HttpStatus.OK);
    }

    // Mostrar paciente según id
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> findById(@PathVariable Long id) {
        Optional<PacienteDTO> pacienteDTO = pacienteService.findById(id);
        return pacienteDTO.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear paciente
    @PostMapping("/crear")
    public ResponseEntity<PacienteDTO> create(@RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO savedPacienteDTO = pacienteService.save(pacienteDTO);
        return new ResponseEntity<>(savedPacienteDTO, HttpStatus.CREATED);
    }

    // Actualizar paciente
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> update(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO updatedPacienteDTO = pacienteService.update(id, pacienteDTO);
        return new ResponseEntity<>(updatedPacienteDTO, HttpStatus.OK);
    }

    // Borrar paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        pacienteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

