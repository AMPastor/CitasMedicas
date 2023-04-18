package com.metaenlace.citasmedicas.controller;

import com.metaenlace.citasmedicas.entitydto.MedicoDTO;
import com.metaenlace.citasmedicas.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
//import java.util.LinkedHashMap;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/lista")
    public ResponseEntity<Object> findAll() {
        List<MedicoDTO> medicosDTO = medicoService.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("Medicos", medicosDTO);
        response.put("Mensaje", "Medicos encontrados correctamente");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            Optional<MedicoDTO> medicoDTO = medicoService.findById(id);
            Map<String, Object> response = new HashMap<>();
            if (medicoDTO.isPresent()) {
                response.put("Medico", medicoDTO.get());
                response.put("Mensaje", "Médico encontrado correctamente");
                return ResponseEntity.ok().body(response);
            } else {
                response.put("Mensaje", "No se encontró ningún médico con el ID especificado");
                response.put("error", "No existe ningún médico con el ID especificado");
                response.put("status", HttpStatus.NOT_FOUND.value());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Error al buscar el médico");
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Object> create(@RequestBody MedicoDTO medicoDTO) {
        MedicoDTO savedMedicoDTO = medicoService.save(medicoDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("Medico", savedMedicoDTO);
        response.put("Mensaje", "Medico creado correctamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) {
        MedicoDTO updatedMedicoDTO = medicoService.update(id, medicoDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("Medico", updatedMedicoDTO);
        response.put("Mensaje", "Medico editado correctamente");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        medicoService.deleteById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("Mensaje", "Medico eliminado correctamente");
        return ResponseEntity.ok(response);
    }


}


