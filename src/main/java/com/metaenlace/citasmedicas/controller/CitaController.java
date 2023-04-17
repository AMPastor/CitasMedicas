package com.metaenlace.citasmedicas.controller;

import com.metaenlace.citasmedicas.entitydto.CitaDTO;
import com.metaenlace.citasmedicas.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cita")
public class CitaController {
    @Autowired
    private CitaService citaService;

    // Crear cita:
    @PostMapping("/crear")
    public ResponseEntity insertar(@RequestBody CitaDTO citaDTO){
        citaService.insertar(citaDTO);
        return ResponseEntity.ok("Cita insertada");
    }

    @GetMapping("/lista")
    public ResponseEntity<List<CitaDTO>> lista(){
        List<CitaDTO> citas = citaService.listaCitas();
        return ResponseEntity.ok(citas);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity editar(@RequestBody CitaDTO citaDTO, @PathVariable Long id){
        citaService.editarCita(id, citaDTO);
        return ResponseEntity.ok("Cita editada");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        citaService.eliminarCita(id);
        return ResponseEntity.ok("Cita eliminada");
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id){
        CitaDTO citaDTO = citaService.buscarCita(id);
        return ResponseEntity.ok(citaDTO);
    }
}