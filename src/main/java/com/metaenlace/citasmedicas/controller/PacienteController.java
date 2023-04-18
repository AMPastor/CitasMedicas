package com.metaenlace.citasmedicas.controller;

import com.metaenlace.citasmedicas.entitydto.PacienteDTO;
import com.metaenlace.citasmedicas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    //Mostrar listado pacientes
    @GetMapping("/lista")
    public ResponseEntity<Object> findAll() {
        List<PacienteDTO> pacientesDTO = pacienteService.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("Pacientes", pacientesDTO);
        response.put("Mensaje", "Pacientes encontrados correctamente");
        return ResponseEntity.ok().body(response);
    }

    //Mostrar paciente según id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            Optional<PacienteDTO> pacienteDTO = pacienteService.findById(id);
            Map<String, Object> response = new HashMap<>();
            if (pacienteDTO.isPresent()) {
                response.put("Paciente", pacienteDTO.get());
                response.put("Mensaje", "Paciente encontrado correctamente");
                return ResponseEntity.ok().body(response);
            } else {
                response.put("Mensaje", "No se encontró ningún paciente con el ID especificado");
                response.put("error", "No existe ningún paciente con el ID especificado");
                response.put("status", HttpStatus.NOT_FOUND.value());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Error al buscar el paciente");
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //Crear paciente
    @PostMapping("/crear")
    public ResponseEntity<Object> create(@RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO savedPacienteDTO = pacienteService.save(pacienteDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("Paciente", savedPacienteDTO);
        response.put("Mensaje", "Paciente creado correctamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Actualizar paciente
    @PutMapping("/editar/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO updatedPacienteDTO = pacienteService.update(id, pacienteDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("Paciente", updatedPacienteDTO);
        response.put("Mensaje", "Paciente actualizado correctamente");
        return ResponseEntity.ok().body(response);
    }


    // Borrar paciente
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        pacienteService.deleteById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("Mensaje", "Paciente eliminado correctamente");
        return ResponseEntity.ok().body(response);
    }

}

