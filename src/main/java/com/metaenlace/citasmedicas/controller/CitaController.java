package com.metaenlace.citasmedicas.controller;

import com.metaenlace.citasmedicas.entitydto.CitaDTO;
import com.metaenlace.citasmedicas.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/citas")
public class CitaController {
    @Autowired
    private CitaService citaService;

    //Mostrar citas
    @GetMapping("/lista")
    public ResponseEntity<Object> findAll() {
        List<CitaDTO> citasDTO = citaService.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("Citas", citasDTO);
        response.put("Mensaje", "Citas encontradas correctamente");
        return ResponseEntity.ok().body(response);
    }

    //Mostrar cita por id
    @GetMapping("/lista/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            CitaDTO citaDTO = citaService.findById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("Cita", citaDTO);
            response.put("Mensaje", "Cita encontrada correctamente");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Error al buscar la cita");
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //Crear cita:
    @PostMapping("/crear")
    public ResponseEntity<Object> create(@RequestBody CitaDTO citaDTO) {
        CitaDTO savedCitaDTO = citaService.create(citaDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("Cita", savedCitaDTO);
        response.put("Mensaje", "Cita creada correctamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Editar cita
    @PutMapping("/editar/{id}")
    public ResponseEntity update(@RequestBody CitaDTO citaDTO, @PathVariable Long id){
        citaService.update(id, citaDTO);
        return ResponseEntity.ok("Cita editada correctamente");
    }

    //Eliminar cita
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        citaService.deleteById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("Mensaje", "Cita eliminada correctamente");
        return ResponseEntity.ok(response);
    }

}