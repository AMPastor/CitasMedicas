package com.metaenlace.citasmedicas.controller;

import com.metaenlace.citasmedicas.entitydto.MedicoDTO;
import com.metaenlace.citasmedicas.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    // Mostrar listado médicos
    @GetMapping("/lista")
    public ResponseEntity<List<MedicoDTO>> findAll() {
        List<MedicoDTO> medicosDTO = medicoService.findAll();
        return new ResponseEntity<>(medicosDTO, HttpStatus.OK);
    }

    // Mostrar medico según id
    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> findById(@PathVariable Long id) {
        Optional<MedicoDTO> medicoDTO = medicoService.findById(id);
        return medicoDTO.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // Crear medico
    @PostMapping("/crear")
    public ResponseEntity<MedicoDTO> create(@RequestBody MedicoDTO medicoDTO) {
        MedicoDTO savedMedicoDTO = medicoService.save(medicoDTO);
        return new ResponseEntity<>(savedMedicoDTO, HttpStatus.CREATED);
    }


    // Actualiar medico
    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> update(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) {
        MedicoDTO updatedMedicoDTO = medicoService.update(id, medicoDTO);
        return new ResponseEntity<>(updatedMedicoDTO, HttpStatus.OK);
    }


    //Borrar medico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        medicoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


